from consul import Consul
import threading
import json


class ConfigResolver:
    def __init__(self, host):
        self.consul = Consul(host)

    def get_key(self, callback, key, infinite=True):
        """
        Resolve a key from consul
        :param callback: the callback to call when a new value as been received
        :param key: the key
        :param infinite: set True to always call the callback after retrieving new data
        """
        thread = threading.Thread(target=self._get_key, args=[callback, key, infinite])
        thread.start()

    def _get_key(self, callback, key, infinite):
        index = None
        print("Start listening for key %s" % key)
        while True:
            index, data = self.consul.kv.get(key, index=index)
            if data is not None:
                if callback:
                    callback(data)
                if not infinite:
                    return data

    def put_key(self, key, values):
        """
        put values for the given key, values may be dict or str
        :param key: the key 
        :param values: the values to put
        """
        if isinstance(values, dict):
            values = json.dumps(values)
        self.consul.kv.put(key, values)

    def get_config(self, config_name):
        """
        resolve the config on the consul server 
        :param config_name: the config's name
        :return: the config
        """
        data = self._get_key(None, config_name, False)['Value']
        data = data.decode('utf-8')
        return _Config(**json.loads(data))


class _Config:
    def __init__(self, host, port, db_host, db_port, *args, **kwargs):
        self.host = host
        self.port = int(port)
        self.db_host = db_host
        self.db_port = int(db_port)

    def to_json(self):
        return json.dumps(self.to_dict())

    def to_dict(self):
        return {
            'host': self.host,
            'port': self.port,
            'db_host': self.db_host,
            'db_port': self.db_port
        }

    def __repr__(self):
        return str(self.to_dict())
