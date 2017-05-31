import consul
import os
import time

if __name__ == "__main__":
    time.sleep(20)
    c = consul.Consul(host="localhost", port=8500)

    gateway_file = open("gateway-service.yml", "r+")
    gateway_string = gateway_file.read()
    gateway_file.close()
    print(gateway_string)
    c.kv.put("config/dev/gateway-service.yml", gateway_string)