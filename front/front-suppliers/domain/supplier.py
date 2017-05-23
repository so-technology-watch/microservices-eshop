from json import dumps
class Supplier:


    def __init__(self, id, company, mail, phone):

        print("ok")
        self.id = id
        self.company = company
        self.mail = mail
        self.phone = phone

    def __repr__(self):
        return str(dumps({
            'id': self.id,
            'company': self.company,
            'mail': self.mail,
            'phone': self.phone
        }))
