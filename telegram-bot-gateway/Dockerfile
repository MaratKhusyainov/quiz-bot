FROM openjdk:8-jre-alpine
RUN apk update; apk install -y fontconfig fonts-liberation
RUN fc-cache -f -v
#RUN apk add font-noto-gujarati font-noto-kannada font-noto-avestan font-noto-osage font-noto-kayahli font-noto-oriya font-noto-telugu font-noto-tamil font-noto-bengali font-noto-malayalam font-noto-arabic font-noto-extra \
#    && fc-cache -f
WORKDIR /telegram-bot-gateway
COPY /telegram-bot-gateway/target/telegram-bot-gateway-0.0.1-SNAPSHOT.jar telegram-bot-gateway.jar
ENTRYPOINT ["java", "-jar", "telegram-bot-gateway.jar"]