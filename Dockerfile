FROM openjdk:17-slim
WORKDIR /usr/src/app
#VOLUME /tmp
ADD build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]