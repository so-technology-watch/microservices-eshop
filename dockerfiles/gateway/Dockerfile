FROM lechelong/eshop-base
RUN apk --update add openjdk8-jre
ADD gateway.jar /gateway.jar
EXPOSE 9090
CMD java -jar gateway.jar