from flask import Flask, render_template, request
from services.products_service import ProductService, Product

app = Flask(__name__)
gateway_url = '10.226.159.191:9090'
products_service = ProductService(gateway_url)

@app.route("/")
def root():
    return render_template("authPage.html")

##Authentification by email
@app.route("/auth")
def auth():
    return "auth route"

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
