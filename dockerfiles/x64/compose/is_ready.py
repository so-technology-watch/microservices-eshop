import requests
import pymongo
import redis
import psycopg2
import pika


def is_amqp_ready():
	try:
		credentials = pika.PlainCredentials('pi', 'pi')
		parameters = pika.ConnectionParameters(credentials=credentials, host="127.0.0.1", virtual_host="/pi", port=5672)
		connection = pika.BlockingConnection(parameters)
		return True
	except Exception as e:
		return False

def is_ready_postgresql():
	try:
		conn = "host='localhost' dbname='postgres' user='postgres' password='postgres'"
		conn = psycopg2.connect(conn)
		cusor = conn.cursor()
		return True
	except Exception as e:
		return False

def is_ready_redis():
	try:
		rs = redis.Redis("localhost")
		rs.get(None)
		return True
	except Exception as e:
		print("exception ! ", e, type(e))
		return False
	

def is_ready_mongo():
	try:
		client = pymongo.MongoClient("mongodb://localhost:27017/posts", serverSelectionTimeoutMS=3000)
		info = client.server_info()
		return True
	except Exception as e:
		return False

def is_ready(url):
	try :
		req = requests.get(url)
		return req.status_code == 200
	except Exception as e:
		return False

def is_consul_ready():
	return is_ready('http://localhost:8500/ui')