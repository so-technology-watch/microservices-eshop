from flask import Flask, render_template

app = Flask(__name__)

@app.route("/")
def root():
    return render_template("authPage.html")

##Authentification by email
@app.route("/auth")
def auth():
    return "auth route"

if __name__ == "__main__":
    app.run(debug = True)
