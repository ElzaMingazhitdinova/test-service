FROM openjdk:8-jre
ENV IGNITE_CLIENT_MODE false
ADD ./target/test-service-1.0-SNAPSHOT.jar /app/test-service-1.0-SNAPSHOT.jar
CMD ["java", "-Xdebug", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005", "-jar", "/app/test-service-1.0-SNAPSHOT.jar"]