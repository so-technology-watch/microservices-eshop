#!/bin/bash
if [ $# -ne 1 ]
then
	echo "CONSUL_CLIENT address needed"
	exit 1
fi
docker run --name carts -p 8082:8082 -e consul=$1 lechelong/carts:latest

exit 0
