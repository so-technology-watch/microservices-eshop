#!/bin/bash
if [ $# -ne 1 ]
then
	echo "CONSUL_CLIENT address needed"
	exit 1
fi
docker run --name service-bills --net=host -p 9091:9091 -e CONSUL_CLIENT=$1 faseldi/bills

exit 0
