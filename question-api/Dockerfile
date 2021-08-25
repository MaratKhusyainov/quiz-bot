FROM openjdk:8-jre-alpine
WORKDIR /question-api
COPY /question-api/target/question-api-0.0.1-SNAPSHOT.jar question-api.jar
ENTRYPOINT ["java", "-jar", "question-api.jar"]