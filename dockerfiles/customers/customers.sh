#!/bin/sh
env = 'consul=10.226.159.191:8500'
sudo docker run -d -e $env -p 8080:8080 -it lechelong/customers:latest

exit 0
