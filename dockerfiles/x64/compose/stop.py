import os
import threading

def kill_all():
	threads = []
	l = ['consul', 'mongodb', 'bills', 'carts', 'carts-db', 'customers', 'rabbitmq', 'products-db', 'products', 'gateway', 'front-eshop', 'front-suppliers']
	for name in l:
		threads.append(threading.Thread( target=lambda : os.system('docker rm -f %s' % name) ))
		threads[-1].start()
	[t.join() for t in threads if t is not None]