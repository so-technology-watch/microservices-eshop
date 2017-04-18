from fr.sogeti.consul.config_resolver import ConfigResolver
from fr.sogeti.consul.service_discovery import ServiceDiscovery
from fr.sogeti.services.bills_service import BillsService as bill_service
import threading
import signal


def deregister(discovery, ids):
    [discovery.deregister(id) for id in ids]
    print("services unregistred")

if "__main__" == __name__:
    import fr.sogeti.rest as rest
    discovery = ServiceDiscovery()
    deregister(discovery, ['bills-http', 'bills-db'])

    discovery.register('bills-http', 'bills-service', '127.0.0.1', 8080, 'http', ('service', 'bills'), route='/api/v1/check')
    discovery.register('bills-db', 'bills-service', '127.0.0.1', 27017, 'tcp', ('database', 'bills'))

    #import atexit
    #atexit.register(at_exit, discovery, ['bills-http', 'bills-db'])

    consul = ConfigResolver("localhost")
    consul.put_key("config/services/bills", {
        'host': 'localhost',
        'port': '8080'
    })
    config = consul.get_config("config/server-bills")
    thread = threading.Thread(target=rest.run, args=[config.host, config.port])
    thread.start()
