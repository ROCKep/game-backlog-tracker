FROM openjdk:11-jre-slim
COPY /target/game-backlog-tracker.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
