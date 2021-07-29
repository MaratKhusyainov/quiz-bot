package ru.gb.questionapi;

import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.gb.questionapi.domain.Question;
import ru.gb.questionapi.parsing.Response;
import ru.gb.questionapi.services.QuestionService;

import java.io.IOException;
import java.util.Objects;

import static ru.gb.questionapi.SendRequest.sendGet;


//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
//@PropertySource("classpath:telegram-bot.secret.properties")
@SpringBootApplication
public class QuestionApiApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(QuestionApiApplication.class, args);

//        QuestionService questionService = new QuestionService();
//        Question question = Question.builder()
//                .question("А и Б сидели на трубе. А упала, Б пропала. Кто остался на трубе?")
//                .answer1("И")
//                .answer2("A")
//                .answer3("Б")
//                .answer4("Никого")
//                .complexity(1)
//                .hash(1234567)
//                .build();
//
//        questionService.saveOrUpdate(question);
//
//        Gson gson = new Gson();
//        Response response = gson.fromJson(sendGet(1,2), Response.class);
//        response.getData();
    }
}
