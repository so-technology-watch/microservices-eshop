# The compose.py file
This file as been designed to compose run all the project on a single windows computer

Make sure you have an internet connection before continuing,
this script will pull all pre-build services from dockerhub

## Requirements
You need to have python3 installed
You need to have docker installed

moreother, you need to install all requirements using the following command :
```sh
$ pip install -r requirements.txt
```

## Command run
If you are using the run command :
```sh
$ python3 compose.py run
```

This will pull all services and run them from dockerhub

## Command stop
If you are using the stop command :
```sh
$ python3 compose.py stop
```
This will kill all docker services launched by the run command

## Modify the services configurations to put on consul *before* running all containers

To modify the configurations, you have to modify the files located into :
PROJECT_ROOT/dockerfiles/x64/consul/kv/

You'll see a files per service. 
If you want to modify the configuration of a service, please modify the file from the same name.
All configurations are in JSON expected gateway-services.yml which is in YAML due to a spring cloud requirement


