FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

COPY . .

RUN --mount=type=secret,id=PORT,dst=/etc/secrets/PORT cp /etc/secrets/PORT PORT
RUN --mount=type=secret,id=MONGODB,dst=/etc/secrets/MONGODB cp /etc/secrets/MONGODB MONGODB
RUN --mount=type=secret,id=JWT_SECRET,dst=/etc/secrets/JWT_SECRET cp /etc/secrets/JWT_SECRET JWT_SECRET

ENV list="PORT MONGODB JWT_SECRET"
RUN echo $list
RUN bash -c 'awk "{print FILENAME\"=\"\$0}" $list > .env'

RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /build/libs/darkube-server-0.1.0.jar darkube-server.jar
COPY --from=build /.env .env

ENTRYPOINT ["java", "-jar", "darkube-server.jar"]
