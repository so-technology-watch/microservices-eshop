FROM armhf/alpine:edge
RUN apk --update add openjdk8-jre
ADD service-products.jar /service-products.jar
EXPOSE 9090
ENV CONSUL_CLIENT 10.226.159.191
CMD java -jar service-products.jar