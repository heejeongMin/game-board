FROM openjdk:17-slim
VOLUME /tmp
ADD /*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]