import os
import time
import threading
import sys
import consul

from compose.run import *
from compose.stop import kill_all

def threaded(func):
	threading.Thread(target=func).start()

def insert_kv_into_consul():
	time.sleep(2)
	c = consul.Consul(host="localhost", port=8500)

	keyval = {
		'gateway' : {
			'file' : "consul/kv/gateway-service.yml",
			'key' : "config/dev/gateway-service.yml"
		},
		'bills' : {
			'file' : "consul/kv/bills",
			'key' : "config/services/bills"
		},
		'carts' : {
			'file' : "consul/kv/carts",
			'key' : "config/services/carts"
		},
		'customers' : {
			'file' : "consul/kv/customers",
			'key' : "config/services/customers"
		},
		'products' : {
			'file' : "consul/kv/products",
			'key' : "config/services/products"
		}
	}
	for index in keyval.keys():
		file = keyval[index]['file']
		key = keyval[index]['key']
		
		file = open(file, "r+")
		text = file.read()
		file.close()
		print("inserting key %s" % key)
		c.kv.put(key, text)

def run_all():
	threaded(run_consul)
	threaded(run_rabbitmq)
	while not is_consul_ready():
		print("waiting for consul to be ready...")
		time.sleep(1)
	while not is_amqp_ready():
		print("waiting for rabbitmq to be ready...")
		time.sleep(1)
	insert_kv_into_consul()
	threaded(run_products_db)
	threaded(run_products)
	threaded(run_bills_db)
	threaded(run_bills)
	threaded(run_customers)
	threaded(run_gateway)
	threaded(run_front_eshop)
	threaded(run_front_suppliers)
	threaded(run_carts_db)
	threaded(run_carts)

if __name__ == '__main__':
	args = sys.argv
	if 'run' in args:
		run_all()
	elif 'stop' in args:
		kill_all()
	else:
		print("please specify the action, run or stop")
