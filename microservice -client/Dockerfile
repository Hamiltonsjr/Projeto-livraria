FROM openjdk:14

MAINTAINER hamiltonsjr

RUN mkdir app

COPY /wait-mysql.sh /app
COPY /target/*.jar /app

ENV MYSQL_USER=hamilton
ENV MYSQL_PASSWORD=123456

EXPOSE 8083:8083

WORKDIR app

ENTRYPOINT java $JAVA_OPTS -jar client-1.0.0-SNAPSHOT-runner.jar

