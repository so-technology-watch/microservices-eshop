# Install the python server

## Firstly install your venv
This will install a venv on the given location

### Creating it
```sh
$ python -m venv /path/to/venv
or
$ python -m virtualenv /path/to/venv
```

### Loading it (Linux)
```sh
source /path/to/venv/Scripts/activate.bat
```
```sh
source /path/to/venv/bin/activate
```

Note : if you don't have virtualenv, install it using the following command:
```sh
$ pip install virtualenv
```

## Secondly install the dependencies

```sh
$ pip install -r requierements.txt
```

## If you want to add some requierements, please use the following command
```sh
$ pip freeze > requierements.txt
```