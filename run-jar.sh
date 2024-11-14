
# build
./gradlew build && ./gradlew bootJar

# move the server jar to root
rm prod-server/*.jar
mv build/libs/darkube-server-0.1.0.jar prod-server
mv prod-server/darkube-server-0.1.0.jar prod-server/darkube-server.jar

java -jar prod-server/darkube-server.jar
