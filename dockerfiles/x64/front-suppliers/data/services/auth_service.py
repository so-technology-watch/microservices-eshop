import requests
import json

class Auth_service:

    urlPrefix = "/api/v1"
    def __init__(self, gatewayURL):
        self.gatewayURL = gatewayURL


    def auth(self, id, ):
        r = requests.get("http://" + self.gatewayURL + self.urlPrefix + "/suppliers/" + id)
        print(r.status_code)
        if r.status_code == 200 :
            return r.text, True
        else:
            return None, False
