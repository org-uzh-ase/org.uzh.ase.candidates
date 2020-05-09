FROM openjdk:11
EXPOSE 8082
CMD ["java", "-Dspring.profiles.active=cloud", "-jar", "candidates-0.0.1-SNAPSHOT.jar"]

COPY target/candidates-0.0.1-SNAPSHOT.jar .