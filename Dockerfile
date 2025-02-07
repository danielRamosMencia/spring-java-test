FROM openjdk:21-jdk-slim
WORKDIR /sompopo
COPY target/*.jar sompopo-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","sompopo-0.0.1-SNAPSHOT.jar"]