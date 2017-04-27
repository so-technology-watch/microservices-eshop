from bottle import get, post, put, delete, request, response, hook

from fr.sogeti.consul.config_resolver import ConfigResolver
from fr.sogeti.services.bills_service import BillsService
from bson.json_util import dumps
from fr.sogeti.entities.bill import from_json as bill_from_json
import os

consul = ConfigResolver(os.environ.get('CONSUL_CLIENT'))
config = consul.get_config("config/services/bills")
bills_service = BillsService(config.db_host, config.db_port, 'db_bills')
invalid_parameters = "Invalid parameters"


@hook('after_request')
def init_response():
    response.content_type = 'application/json'
    response.headers['Access-Control-Allow-Origin'] = '*'


@get('/api/v1/bills')
def get_all():
    response.status = 206
    begin, end = get_range()
    response.headers['Content-Range'] = '%d-%d' % (begin, end)
    response.headers['Accept-Range'] = 'bill 10'
    bills = bills_service.get_all(begin, end)

    return dumps(bills)


@get('/api/v1/bills/<id_bill>')
def get_by_id(id_bill):
    bill = bills_service.get_by_id(id_bill)
    if bill is None:
        response.status = 404
        return None
    return dumps(bill)


@post('/api/v1/bills')
def create_bill():
    try:
        bill = get_bill_from_body()
        response.status = 201
        return dumps(bills_service.create(bill))
    except TypeError:
        return error_handler(400, invalid_parameters)


@put('/api/v1/bills')
def update_bill():
    try:
        bill = get_bill_from_body()
        bills_service.update(bill)
        return "OK"
    except TypeError:
        return error_handler(400, invalid_parameters)


@delete('/api/v1/bills/<id_bill>')
def delete_bill(id_bill):
    try:
        bills_service.delete(id_bill)
        return "OK"
    except TypeError:
        return error_handler(400, invalid_parameters)


def error_handler(code, message):
    response.status = code
    return dumps({
        'code': code,
        'message': message
    })


def get_bill_from_body():
    body = request.body
    body_str = body.read().decode('utf-8')
    return bill_from_json(body_str)


def get_range():
    """
    :return: get the range in the request, otherwise 0,10 
    """
    begin, end = 0, 10
    if 'range' in request.params:
        range = request.params['range']
        try:
            begin_tmp, end_tmp = [int(v) for v in range.split('-')]
            if begin_tmp > end_tmp:
                begin_tmp, end_tmp = end_tmp, begin_tmp
            if end_tmp - begin_tmp > 10:
                end_tmp = begin_tmp + 10
            begin, end = begin_tmp, end_tmp
        except (AttributeError, ValueError):
            pass
    return begin, end
