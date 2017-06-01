# Demo Microservices : Sogi-Shop
Démo Microservices : Site de ventes en ligne

# START
Run the python script from ```dockerfiles/x64``` :
 * First install Python 3.X on your machine
 * Install dependencies: ```pip install -r requirements.txt```
 * Run script to launch docker containers : ```python compose.py run```
 * Go the URL of docker on the port 80

# Project Structure

* /gateway : Microservices Gateway based on Spring Cloud
* /services : 
  * /bills :
  * /carts :
  * /customers :
  * /profducts :
* /front :
  * /front-eshop :
  * /front-suppliers :
* /dockerfiles :
  * /x64 : Docker files to launch the microservices applications => Run compose.py with python
  * /
* /scenario-tests : Test cases to check if the domain model is correct
