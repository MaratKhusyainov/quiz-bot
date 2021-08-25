FROM openjdk:8-jre-alpine
WORKDIR /telegram-bot-gateway
COPY /telegram-bot-gateway/target/telegram-bot-gateway-0.0.1-SNAPSHOT.jar telegram-bot-gateway.jar
ENTRYPOINT ["java", "-jar", "telegram-bot-gateway.jar"]