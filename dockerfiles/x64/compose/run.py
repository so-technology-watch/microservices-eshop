from compose.is_ready import *
import os
import time

CONSUL_CLIENT = 'localhost'
local = '127.0.0.1'
lechelong_consul = "-e CONSUL_CLIENT=%s:8500 -e consul=%s:8500" % (CONSUL_CLIENT, CONSUL_CLIENT)


def run_rabbitmq():
	cmd = 'docker run --hostname 0.0.0.0 --name rabbitmq -p 5672:5672 -e RABBITMQ_DEFAULT_VHOST=/pi -e RABBITMQ_DEFAULT_USER=pi -e RABBITMQ_DEFAULT_PASS=pi rabbitmq:3'
	os.system(cmd)

def run_consul():
	cmd = 'docker run --name consul -p 8500:8500 lechelong/consulx64'
	os.system(cmd)

def run_customers():
	cmd = 'docker run --name customers --net=host -p 8080:8080 %s lechelong/customersx64' % lechelong_consul
	os.system(cmd)

def run_carts_db():
	cmd = 'docker run --name carts-db -p 6379:6379 lechelong/carts-dbx64'
	os.system(cmd)

def run_carts():
	while not is_ready_redis():
		print("waiting for redis database...")
		time.sleep(1)	
	cmd = 'docker run --name carts --net=host -p 8082:8082 -e CONSUL_CLIENT=%s:8500 lechelong/cartsx64' % local
	os.system(cmd)

def run_bills():
	while not is_ready_mongo():
		print("waiting for bills database")
		time.sleep(1)
	cmd = 'docker run --name bills --net=host -p 9091:9091 -e CONSUL_CLIENT=%s faseldi/billsx64' % CONSUL_CLIENT
	os.system(cmd)

def run_bills_db():
	cmd = 'docker run --name mongodb -p 27017:27017 mongo'
	os.system(cmd)

def run_products():
	while not is_ready_postgresql():
		print("waiting for postgres database")
		time.sleep(1)
	cmd = 'docker run --name products --net=host -p 9092:9092 -e CONSUL_CLIENT=%s faseldi/productsx64' % CONSUL_CLIENT
	os.system(cmd)

def run_products_db():
	cmd = 'docker run --name products-db -p 127.0.0.1:5432:5432 faseldi/products-dbx64'
	os.system(cmd)

def run_gateway():
	cmd = 'docker run --name gateway --net=host -p 9090:9090 -e CONSUL_CLIENT=%s faseldi/gatewayx64' % CONSUL_CLIENT
	os.system(cmd)

def run_front_suppliers():
	cmd = 'docker run --name front-suppliers -p 91:80 lechelong/front-suppliers'
	os.system(cmd)

def run_front_eshop():
	cmd = 'docker run --name front-eshop -p 90:80 lechelong/front-eshop'
	os.system(cmd)