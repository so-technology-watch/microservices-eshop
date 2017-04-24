#!/bin/bash
if [ $# -ne 1 ]
then
	echo "CONSUL_CLIENT address needed"
	exit 1
fi
docker run --name customers --net=host -p 8080:8080 -e consul=$1 lechelong/customers:latest

exit 0
