#!/bin/bash
if [ $# -ne 1 ]
then
	echo "CONSUL_CLIENT address needed"
	exit 1
fi
docker run --name service-products --net=host -p 27017:27017 -e CONSUL_CLIENT=$1 faseldi/products

exit 0
