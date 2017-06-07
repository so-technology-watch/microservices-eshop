#!/bin/bash
if [ $# -ne 1 ]
then
	echo "CONSUL_CLIENT address needed"
	exit 1
fi
docker run --name gateway --net=host -p 9090:9090 -e CONSUL_CLIENT=$1 faseldi/gateway

exit 0
