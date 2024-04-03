FROM openjdk:11
COPY target/rnd-springboot-app-1.0-SNAPSHOT.jar /app.jar
EXPOSE 8080

CMD ["java", "-jar", "/app.jar"]