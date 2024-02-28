FROM amazoncorretto:17.0.10

WORKDIR /app

COPY target/nasa-asteroids-0.0.1-SNAPSHOT.jar /app/nasa-asteroids-0.0.1-SNAPSHOT.jar

EXPOSE 8001

CMD ["java", "-jar", "nasa-asteroids-0.0.1-SNAPSHOT.jar"]
