from bson import ObjectId
from bson.errors import InvalidId
from pymongo import MongoClient


class MongoDAO:
    def __init__(self, host, port, database, database_name):
        self.client = MongoClient(host, port)
        self.database = self.client[database]
        self.posts = self.database[database_name]

    """
        get all the entity for the given collection
    """
    def get_all(self):
        return self.posts.find({})

    """
        get an entity by id
        :return the entity wanted
    """
    def get_by_id(self, id):
        try:
            return self.posts.find_one({'_id': ObjectId(id)})
        except InvalidId:
            return None

    """
        create an entity
        :return it's id
    """
    def create(self, entity):
        return self.posts.insert_one(entity.to_dict()).inserted_id

    """
        update an entity
    """
    def update(self, entity):
        self.posts.update(entity.get_spec(), entity.to_dict())

    """
        delete an entity
    """
    def delete(self, id):
        id = {
            '_id': ObjectId(id)
        }
        self.posts.delete_one(id)
