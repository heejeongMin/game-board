FROM openjdk:17-slim
VOLUME /tmp
ADD /game-0.0.3-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]