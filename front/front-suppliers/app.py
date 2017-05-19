from flask import Flask

app = Flask(__name__)

@app.route("/")
def root():
    return "I'm just a supplier front, get over it"


if __name__ == "__main__":
    app.run()
