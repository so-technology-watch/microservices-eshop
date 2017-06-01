import requests
import json
from domain.supplier import Supplier

class Supplier_service:

    urlPrefix = "/api/v1"
    
    def __init__(self, gatewayURL):
        self.gatewayURL = gatewayURL


    def get_supplier(self, ID):
        r = requests.get("http://" + self.gatewayURL + self.urlPrefix + "/suppliers/" + str(ID))
        if r.status_code == 200 :
            print(Supplier(**json.loads(r.text)).company)
            return Supplier(**json.loads(r.text))
        else:
            return None


    def update_supplier(self, ID, supplier):
        url = "http://%s%s/suppliers" %  (self.gatewayURL, self.urlPrefix)
        r = requests.put(url, data=str(supplier) , headers={
    		'Content-Type': 'application/json'
		})
        print(r.status_code, str(supplier), url)
        if r.status_code == 200 :
            print("rep", r.text)
            return True
        else:
            return False
