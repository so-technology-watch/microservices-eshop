from bson.json_util import loads


class Supplier:
    def __init__(self, id, company_name, email, phone_number):
        self.id = id
        self.company_name = company_name
        self.email = email
        self.phone_number = phone_number

    def to_dict(self):
        return {
            'id': self.id,
            'company_name': self.company_name,
            'email': self.email,
            'phone_number': self.phone_number
        }

    def __repr__(self):
        return str(self.to_dict())


def from_json(str_json):
    return Supplier(** loads(str_json))


def from_dict(dict):
    return Supplier(**dict)
