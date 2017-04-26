from fr.sogeti.rest.bills_resource import *
from fr.sogeti.rest.payment_resource import *


@get('/api/v1/check')
def check():
    return "ok"


def run(host, port):
    from bottle import run
    run(host=host, port=port)
