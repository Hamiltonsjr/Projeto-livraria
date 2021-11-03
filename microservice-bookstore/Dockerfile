FROM openjdk:14

MAINTAINER hamiltonsjr

RUN mkdir app

COPY /target/*.jar /app

EXPOSE 8082:8082

WORKDIR app

ENTRYPOINT java $JAVA_OPTS -jar bookstore-0.0.1-SNAPSHOT.jar