FROM alpine:latest
RUN apk --update add openjdk8-jre
ADD customers.jar /customers.jar
EXPOSE 8080
CMD java -jar customers.jar server
