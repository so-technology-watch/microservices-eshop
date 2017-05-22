import requests
from json import loads, dumps

class ProductService:

    get_route = "/api/v1/products?idSupplier=%s"
    get_categories_route = "/api/v1/categories"
    post_route = "/api/v1/products"

    def __init__(self, gateway_url):
        self.gateway_url = gateway_url

    def retrieve_products(self, id):
        search = self.get_route % id
        route = "http://%s%s" % (self.gateway_url, search)
        req = requests.get(route)
        return loads(req.text)

    def retrieve_categories(self):
    	route = "http://%s%s" % (self.gateway_url, self.get_categories_route)
    	return loads(requests.get(route).text)

    def update_product(self, product):
    	url = "http://%s%s" % (self.gateway_url, self.post_route)
    	req = requests.put(url, data=str(product), headers={
    		'Content-Type': 'application/json'	
		})
    	return req.text

    def create_product(self, product):
    	url = "http://%s%s" % (self.gateway_url, self.post_route)
    	dic = product.to_dict()
    	del dic['id']
    	req = requests.post(url, data=str(dumps(dic)), headers={
    		'Content-Type': 'application/json'	
		})
    	return req.text

class Product:

	def __init__(self, id, designation, reference, price, description, image, id_supplier, id_category, *args, **kwargs):
		self.id = id
		self.designation = designation[0]
		self.reference = reference[0]
		self.price = price[0]
		self.description = description[0]
		self.image = image[0]
		self.id_category = int(id_category[0])
		self.id_supplier = int(id_supplier)

	def to_dict(self):
		return {
			'id': self.id,
			'designation': self.designation,
			'reference': self.reference,
			'price': self.price,
			'description': self.description,
			'idSupplier': self.id_supplier,
			'image': self.image,
			'idCategory': self.id_category
		}

	def __repr__(self):
		return str(dumps(self.to_dict()))