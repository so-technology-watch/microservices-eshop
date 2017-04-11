from bottle import get, post, put, delete, request, response
from fr.sogeti.services.bills_service import BillsService
from bson.json_util import dumps
from fr.sogeti.entities.bill import from_json as bill_from_json


bills_service = BillsService('localhost', 27017, 'db_bills')
invalid_parameters = "Invalid parameters"


@get('/api/v1/bills')
def get_all():
    response.content_type = 'application/json'
    bills = bills_service.get_all()
    return dumps(bills)


@get('/api/v1/bills/<id_bill>')
def get_by_id(id_bill):
    response.content_type = 'application/json'
    return dumps(bills_service.get_by_id(id_bill))


@post('/api/v1/bills')
def create_bill():
    response.content_type = 'application/json'
    try:
        bill = get_bill_from_body()
        return dumps(bills_service.create(bill))
    except TypeError:
        return error_handler(400, invalid_parameters)


@put('/api/v1/bills')
def update_bill():
    try:
        response.content_type = 'application/json'
        bill = get_bill_from_body()
        bills_service.update(bill)
        return "OK"
    except TypeError:
        return error_handler(400, invalid_parameters)


@delete('/api/v1/bills/<id_product>')
def delete_bill(id_product):
    try:
        response.content_type = 'application/json'
        bills_service.delete(id_product)
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
