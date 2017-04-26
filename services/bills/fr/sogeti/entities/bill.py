from fr.sogeti.entities.supplier import from_dict as supplier_from_dict
from fr.sogeti.entities.customer import from_dict as customer_from_dict
from fr.sogeti.entities.product import from_dict as product_from_dict
from bson import ObjectId
from bson.json_util import loads


class Bill:

    def __init__(self, id=None, products=None, supplier=None, customer=None, total=None, *args, **kwargs):
        self.id = id
        self.products = [product_from_dict(p) for p in products]
        self.supplier = supplier_from_dict(supplier)
        self.customer = customer_from_dict(customer)
        self.total = total

    def to_dict(self):
        res = {
            'products': [p.to_dict() for p in self.products],
            'supplier': self.supplier.to_dict(),
            'customer': self.customer.to_dict(),
            'total': self.total
        }
        if not isinstance(id, ObjectId) and self.id is not None and len(self.id) == 24:
            res['_id'] = ObjectId(self.id)
        return res

    def get_spec(self):
        return {
            '_id': ObjectId(self.id)
        }

    def __repr__(self):
        return str(self.to_dict())


def from_json(str_json):
    return Bill(** loads(str_json))
