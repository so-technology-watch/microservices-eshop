from bson import ObjectId
from bson.errors import InvalidId
from pymongo import MongoClient


class MongoDAO:
    def __init__(self, host, port, database, database_name):
        print(host, port)
        self.client = MongoClient(host, port)
        self.database = self.client[database]
        self.posts = self.database[database_name]

    def get_all(self, begin, end):
        """
        get all the entity for the given collection
        :return: the entities
        """
        return self.posts.find().skip(begin).limit(end-begin)

    def get_by_id(self, id):
        """
        get an entity by id
        :param id: 
        :return: the entity wanted
        """
        try:
            return self.posts.find_one({'_id': ObjectId(id)})
        except InvalidId:
            return None

    def get_by_user(self, id):
        """
        get the entities for the given user id
        :param id:
        :return: the entities wanted
        """
        try:
            id = int(id)
            return [bill for bill in self.posts.find({'customer': {'id': id}})]
        except:
            return None

    def create(self, entity):
        """
        create an entity
        :param entity: the entity to create
        :return: the id of the created entity 
        """
        return self.posts.insert_one(entity.to_dict()).inserted_id

    def update(self, entity):
        """
        update an entity
        :param entity: the entity to update
        """
        self.posts.update(entity.get_spec(), entity.to_dict())

    def delete(self, id):
        """
        delete an entity
        :param id: the id of the entity
        """
        id = {
            '_id': ObjectId(id)
        }
        self.posts.delete_one(id)
