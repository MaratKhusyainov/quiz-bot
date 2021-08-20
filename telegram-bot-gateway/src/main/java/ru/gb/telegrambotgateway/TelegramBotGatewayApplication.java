package ru.gb.telegrambotgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:telegram-bot-secret.properties")
public class TelegramBotGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramBotGatewayApplication.class, args);

    }
}
