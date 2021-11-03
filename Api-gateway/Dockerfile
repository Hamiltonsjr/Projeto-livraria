FROM openjdk:14

MAINTAINER hamiltonsjr

RUN mkdir app

COPY /target/*.jar /app

EXPOSE 9999:9999

WORKDIR app

ENTRYPOINT java $JAVA_OPTS -jar api-gateway-0.0.1-SNAPSHOT.jar
