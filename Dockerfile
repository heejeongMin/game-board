FROM openjdk:17-slim
VOLUME /tmp
ADD /build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]