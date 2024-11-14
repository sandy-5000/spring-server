FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

COPY prod-server/darkube-server.jar .

FROM openjdk:17-jdk-slim
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "darkube-server.jar"]
