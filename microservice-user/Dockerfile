FROM openjdk:14

MAINTAINER hamiltonsjr

RUN mkdir app

COPY /target/*.jar /app

EXPOSE 8084:8084

WORKDIR app

ENTRYPOINT java $JAVA_OPTS -jar user-microservice-pag-0.0.1-SNAPSHOT.jar