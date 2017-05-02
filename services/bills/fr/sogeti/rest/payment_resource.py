import os
from bottle import post

from fr.sogeti.consul.config_resolver import ConfigResolver
from fr.sogeti.services.bills_service import BillsService
from fr.sogeti.services.payment_service import PaymentService

consul = ConfigResolver(os.environ.get('CONSUL_CLIENT'))
config = consul.get_config("config/services/bills")
bills_service = BillsService(config.db_host, config.db_port, 'db_bills')
payment_service = PaymentService(bills_service)


@post('/api/v1/pay/<id_user>')
def pay(id_user):
    return payment_service.create(id_user, config.gateway)
