FROM openjdk:14

MAINTAINER hamiltonsjr

RUN mkdir app

COPY /target/*.jar /app

EXPOSE 8085:8085

WORKDIR app

ENTRYPOINT java $JAVA_OPTS -jar sale-1.0.0-SNAPSHOT-runner.jar


