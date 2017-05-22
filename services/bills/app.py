from fr.sogeti.consul.config_resolver import ConfigResolver
from fr.sogeti.consul.service_discovery import ServiceDiscovery
import threading
import os


def deregister(discovery, ids):
    [discovery.deregister(id) for id in ids]
    print("services unregistred")

if "__main__" == __name__:
    import fr.sogeti.rest as rest
    consul_client = os.environ.get('CONSUL_CLIENT')
    if consul_client is None:
        print("CONSUL_CLIENT environment variable not set properly")
        exit(1)

    discovery = ServiceDiscovery(consul_client)
    deregister(discovery, ['bills-http'])

    consul = ConfigResolver(consul_client)
    config = consul.get_config("config/services/bills")
    if config.accept_all:
        address = '0.0.0.0'
    else:
        address = consul.get_address(config.interface)
    discovery.register('bills-http', 'bills-service', address, config.port, 'http', ('service', 'bills'), route='/api/v1/check')

    thread = threading.Thread(target=rest.run, args=[address, config.port])
    thread.start()
    print("running on http://%s:%s" % (address, config.port))
