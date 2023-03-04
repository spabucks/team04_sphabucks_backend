FROM openjdk:17-jdk-slim

COPY ./build/libs/spharos-academy-clone-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
