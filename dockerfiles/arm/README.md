To run on a raspberry :

use the run.sh script in the docker
to use it, please specify the consul_client

consul client is the ip address of consul, for instance for localhost use : 127.0.0.1
for both carts and customers services ou must specify the consul's port, for instance ./run.sh 127.0.0.1

all docker images are deposed on dockerhub, so they are downloaded automatically.

to run a docker instance each time you boot, you have to possibility to do the following steps :

* add : docker rm -f service-name (the service-name is the name of your service, for instance service-bills)
* do  : sudo contrab -e
* add : @reboot /home/user/run.sh my-consul-ip (user is you current user adn my-consul-ip is the ip of consul)

now each time you reboot the container will reset


MAKE SURE :
  * consul is launched before lauching a service
  * rabbitmq is launched before lauching a service
  * the configuration of the service is available on consul : you have examples at /dockerfiles/x64/consul/kv
  
