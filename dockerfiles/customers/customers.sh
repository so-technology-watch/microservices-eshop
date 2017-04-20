#!/bin/sh
env = '10.226.159.191:8500'
sudo docker run -d -e $env -p 8082:8082 -it lechelong/customers:latest
