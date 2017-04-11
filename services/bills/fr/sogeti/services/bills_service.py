from fr.sogeti.dao.mongo_dao import MongoDAO


class BillsService:
    def __init__(self, host, port, database):
        self.dao = MongoDAO(host, port, database, 'bills')

    def get_all(self):
        return self.dao.get_all()

    def get_by_id(self, id):
        return self.dao.get_by_id(id)

    def create(self, bill):
        return self.dao.create(bill)

    def update(self, bill):
        self.dao.update(bill)

    def delete(self, id):
        self.dao.delete(id)
