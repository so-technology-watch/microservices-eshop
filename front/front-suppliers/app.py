# coding=utf-8
from services.products_service import ProductService, Product
from services.auth_service import Auth_service
from services.supplier_service import Supplier_service
from domain.supplier import Supplier
from flask import Flask, render_template, request, session, url_for, redirect, flash
from json import loads

auth_service = Auth_service()
supplier_service = Supplier_service()
app = Flask(__name__)
gateway_url = '10.226.159.191:9090'
products_service = ProductService(gateway_url)
app.secret_key = "this_is_a_secret_key_get_over_it"

@app.errorhandler(404)
def not_found(e):
	return render_template("notFound.html"), 404

@app.errorhandler(500)
def everything_is_broken(e):
	return render_template("serverError.html"), 500

@app.route("/")
def root():
    if "logged" in session and session["logged"] == "TRUE" :
        return render_template("home.html")
    else :
        return render_template("authPage.html")

##Authentification by id
@app.route("/auth", methods=["POST"])
def auth():
	id = request.form["ID"]

	if id == "":
		logged = False
	else:
		supplier, logged = auth_service.auth(id)

	if logged:
		session["logged"] = "TRUE"
		session["supplier"] = loads(supplier)
		flash("Authentification avec succès.", "message")
	else:
		session["logged"] = "FALSE"
		flash("Impossible de vous identifier, veuillez vérifier que votre ID est correct.", "error")

	return redirect(url_for("root"))

@app.route("/logout")
def logout():
    if "logged" in session and session["logged"] == "TRUE" :
        session.clear()
        flash("Déconnecté avec succès.", "message")
    return redirect(url_for("root"))

@app.route("/products")
def products_list():
	if not is_logged():
		return redirect(url_for("root"))
	products = products_service.retrieve_products(get_id_supplier())
	categories = products_service.retrieve_categories()
	session['active'] = 'products'
	return render_template("products/products.html", products=products, categories=categories)

@app.route("/addproduct")
def add_product():
	if not is_logged():
		return redirect(url_for("root"))
	session['active'] = 'add'
	categories = products_service.retrieve_categories()
	return render_template("products/addproduct.html", categories=categories)

@app.route("/updateinfo")
def update_info():
	if not is_logged():
		return redirect(url_for("root"))
	session['active'] = 'updateinfo'
	id = get_id()
	supplier = supplier_service.get_supplier(id)
	return render_template("suppliers/updateSupplier.html", supplier=supplier)

@app.route("/supplierUdapte", methods=["POST"])
def supplier_update():
	if not is_logged():
	 	return redirect(url_for("root"))
	id = get_id()
	dic = {}
	for key in request.form.keys():
		dic[key]=request.form[key]
	supplier_service.update_supplier(id, Supplier(id, **dic))
	flash("Informations modifiées avec succès", "message")
	return redirect(url_for("update_info"))

@app.route("/createProduct", methods=['POST'])
def create_product():
	if not is_logged():
		return redirect(url_for("root"))
	product = Product(id='', id_supplier=get_id_supplier(), **request.form)
	res = products_service.create_product(product)
	if res == 'OK':
		message = "Impossible de créer votre produit"
	else:
		message = "Produit créé avec succès"
	flash("Produit ajouté avec succès", "message")
	return redirect(url_for("add_product"))

@app.route("/updateProduct/<id>", methods=['POST'])
def update_product(id):
	if not is_logged():
		return redirect(url_for("root"))
	product = Product(id, id_supplier=get_id_supplier(), **request.form)
	products_service.update_product(product)
	flash("Produit modifié avec succès", "message")
	return redirect(url_for("update_product"))

@app.route("/deleteProduct/<id>", methods=['DELETE'])
def delete_product(id):
	if not is_logged():
			return redirect(url_for("root"))
	products_service.delete_product(id)
	return "OK"

def get_id_supplier():
	return session["supplier"]['id']

def is_logged():
	return "logged" in session and session["logged"] == "TRUE"

def get_id():
	return session["supplier"]['id']

if __name__ == "__main__":
    app.run()
