import requests
from json import loads, dumps

class ProductService:

    get_route = "/api/v1/products?idSupplier=%s"
    post_route = "/api/v1/products"

    def __init__(self, gateway_url):
        self.gateway_url = gateway_url

    def retrieve_products(self, id):
        search = self.get_route % id
        route = "http://%s%s" % (self.gateway_url, search)
        print(route)
        req = requests.get(route)
        return loads(req.text)

    def update_product(self, product):
    	req = requests.put("http://%s%s", (self.get_route, self.gateway_url))
    	return req.text

class Product:

	def __init__(self, designation, reference, price, *args, **kwargs):
		self.designation = designation
		self.reference = reference
		self.price = price

	def __repr__(self):
		return str(dumps({
			'designation': self.designation,
			'reference': self.reference,
			'price': self.price
		}))