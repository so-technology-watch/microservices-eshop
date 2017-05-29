FROM lechelong/eshop-base
RUN   apk update \
 &&   apk add ca-certificates wget \
 &&   update-ca-certificates
 RUN apk --update add python3-dev build-base linux-headers pcre-dev
RUN apk add unzip
RUN wget https://releases.hashicorp.com/consul/0.8.3/consul_0.8.3_linux_amd64.zip?_ga=2.19893498.39861759.1495553220-1827631697.1490875295
RUN unzip consul_0.8.3_linux_amd64.zip?_ga=2.19893498.39861759.1495553220-1827631697.1490875295  -d consul
WORKDIR /consul
ADD datadir datadir
ADD gateway-service.yml gateway-service.yml
ADD run.py run.py
RUN pip3 install python-consul
RUN ls
EXPOSE 8500
CMD ./consul agent -node node1 -server -bootstrap-expect 1 -data-dir ./datadir -client 0.0.0.0 -advertise 172.17.42.1 -ui | python3 run.py 
