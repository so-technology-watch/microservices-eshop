import consul


class ServiceDiscovery:
    def __init__(self):
        self.consul = consul.Consul()

    def register(self, id, name, address, port, protocol, tags=tuple(), interval=30, timeout=2, route=''):
        """
        Register a service to consul
        :param id: the id we want to register 
        :param name: the name of the service
        :param address: the remote address of the service
        :param port: the remote requested port of the service
        :param protocol: the protocol used, http, tcp, udp, ...
        :param tags: the tags
        :param interval: the interval of checking if the process is alive
        :param timeout: the timeout that indacates to consul that the service is down
        :param route: the route for checking if the service is available
        """
        if protocol == 'http':
            url = 'http://%s:%d%s' % (address, port, route)
        else:
            url = '%s:%d' % (address, port)

        params = {
            'name': name,
            'service_id': id,
            'address': address,
            'port': port,
            'tags': tags,
            'check': {
                'id': id,
                'name': '%s on port %d' % (name, port),
                protocol: url,
                'interval': '%ds' % interval,
                'timeout': '%ds' % timeout
            }
        }
        self.consul.agent.service.register(**params)
        print('registered on consul with parameters %s' % str(params) )

    def deregister(self, id):
        """
        Deregister a service from consul
        :param id: the id we want to deregister
        """
        self.consul.agent.service.deregister(id)
