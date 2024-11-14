
# build
./gradlew build && ./gradlew bootJar

# move the server jar to root
mv build/libs/darkube-server-0.1.0.jar ./prod-server
