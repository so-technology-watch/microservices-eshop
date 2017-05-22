from services.products_service import ProductService, Product
from services.auth_service import Auth_service
from flask import Flask, render_template, request, session, url_for, redirect, flash

auth_service = Auth_service()
app = Flask(__name__)
gateway_url = '10.226.159.191:9090'
products_service = ProductService(gateway_url)
app.secret_key = "this_is_a_secret_key_get_over_it"

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
    supplier, logged = auth_service.auth(id)

    if logged:
        session["logged"] = "TRUE"
        session["supplier"] = supplier
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
    id = 141
    products = products_service.retrieve_products(id)
    categories = products_service.retrieve_categories()
    return render_template("products/products.html", products=products, categories=categories)

@app.route("/updateProduct/<id>", methods=['GET','POST'])
def update_product(id):
    if request.method == 'POST':
        product = Product(id, id_supplier=141, **request.form)
        products_service.update_product(product)
    return render_template("products/success.html", previous_page="/products")

if __name__ == "__main__":
    app.run(debug = True)
