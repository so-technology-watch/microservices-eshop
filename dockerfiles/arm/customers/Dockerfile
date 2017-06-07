FROM lechelong/eshop-base
RUN apk --update add openjdk8-jre
ADD customers.jar /customers.jar
ADD customers.db /customers.db
EXPOSE 8080
CMD java -jar customers.jar server
