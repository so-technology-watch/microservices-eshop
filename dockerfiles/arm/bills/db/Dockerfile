#FROM lechelong/eshop-base
FROM resin/rpi-raspbian:jessie

RUN mkdir -p /data/db

WORKDIR /app
ADD . /app

EXPOSE 27017

CMD ["mongod"]
#ENTRYPOINT ["tail", "-f", "/dev/null"]