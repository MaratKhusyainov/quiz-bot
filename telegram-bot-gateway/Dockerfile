FROM openjdk:8-jre-alpine
RUN apk --no-cache add msttcorefonts-installer fontconfig && \
    update-ms-fonts && \
    fc-cache -f
#RUN apk add font-noto-gujarati font-noto-kannada font-noto-avestan font-noto-osage font-noto-kayahli font-noto-oriya font-noto-telugu font-noto-tamil font-noto-bengali font-noto-malayalam font-noto-arabic font-noto-extra \
#    && fc-cache -f
WORKDIR /telegram-bot-gateway
COPY /telegram-bot-gateway/target/telegram-bot-gateway-0.0.1-SNAPSHOT.jar telegram-bot-gateway.jar
ENTRYPOINT ["java", "-jar", "telegram-bot-gateway.jar"]
