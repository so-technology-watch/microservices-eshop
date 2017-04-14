from bson.json_util import loads


class Product:
    def __init__(self, id, reference, designation, price):
        self.id = id
        self.reference = reference
        self.designation = designation
        self.price = price

    def to_dict(self):
        return {
            'id': self.id,
            'reference': self.reference,
            'designation': self.designation,
            'price': self.price
        }

    def __repr__(self):
        return str(self.to_dict())


def from_json(str_json):
    return Product(**loads(str_json))


def from_dict(dict):
    return Product(**dict)