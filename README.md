# Demo Microservices : Sogi-Shop
Démo Microservices : Site de ventes en ligne

# START
Run the python script from ```dockerfiles/x64``` :
 * First install Python 3.X on your machine
 * Install dependencies: ```pip install -r requirements.txt```
 * Run script to launch docker containers : ```python compose.py run```
 * Go the URL of docker on the port 80

# Project Structure

* /gateway : Microservices Gateway based on Spring Cloud NETFLIX
* /services : The diffrent microservices.
  * /bills : Service written in Python3 using mongoD, dealing with bills an payement management.
  * /carts : Service written Golang 1.8 using Redis, dealing with carts management.
  * /customers : Service written in Java 8 using MapDB, dealing with customers management. 
  * /profducts : Service writtten in Scala using PostgreSQL, dealing with product management.
* /front : The different fronts interactinct with the microservice platform.
  * /front-eshop : Primary front aimed at consumers.
  * /front-suppliers : Second fornt aimed at suppliers.
* /dockerfiles : The different dockerfiles 
  * /x64 : Docker files to launch the different microservices applications for amd64 architecture. => Run compose.py with python
  * arm : Docker files to lauch the diffrent microservices applications for arm architecture.
* /scenario-tests : Test cases to check if the domain model is correct
