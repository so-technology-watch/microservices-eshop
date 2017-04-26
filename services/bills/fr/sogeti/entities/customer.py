from bson.json_util import loads


class Customer:
    def __init__(self, id=None, *args, **kwargs):
        self.id = id

    def to_dict(self):
        return {
            'id': self.id
        }

    def __repr__(self):
        return str(self.to_dict())


def from_json(str_json):
    return Customer(**loads(str_json))


def from_dict(dict):
    return Customer(**dict)
