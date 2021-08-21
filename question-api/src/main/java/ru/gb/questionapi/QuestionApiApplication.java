package ru.gb.questionapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import java.io.IOException;

@SpringBootApplication
@PropertySource("classpath:question-api-secret.properties")
public class QuestionApiApplication {


    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(QuestionApiApplication.class, args);
    }
//    @EventListener(ApplicationReadyEvent.class)
//    private void test() throws JsonProcessingException {

}