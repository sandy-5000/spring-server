FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

COPY . .
RUN ./gradlew bootJar --no0-daemon

FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /build/libs/darkube-server-0.1.0.jar darkube-server.jar

ENTRYPOINT ["java", "-jar", "darkube-server.jar"]
