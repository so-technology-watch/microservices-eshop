FROM lechelong/eshop-base
RUN   apk update \
 &&   apk add ca-certificates wget \
 &&   update-ca-certificates
 RUN apk --update add python3-dev build-base linux-headers pcre-dev
WORKDIR /consul
ADD gateway-service.yml gateway-service.yml
ADD run.py run.py
RUN pip3 install python-consul
RUN ls
CMD python3 run.py
