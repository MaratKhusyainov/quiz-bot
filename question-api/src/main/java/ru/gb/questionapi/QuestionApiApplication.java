package ru.gb.questionapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;
import ru.gb.questionapi.domain.Question;
import ru.gb.questionapi.parsing.Response;
import ru.gb.questionapi.services.QuestionService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Objects;

import static ru.gb.questionapi.SendRequest.giveMeResponse;
import static ru.gb.questionapi.SendRequest.sendGet;


//@SpringBootApplication(exclude = {JacksonAutoConfiguration.class })
@SpringBootApplication
@PropertySource("classpath:telegram-bot-secret.properties")
public class QuestionApiApplication {

    @Autowired
    private QuestionService questionService;


    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(QuestionApiApplication.class, args);
    }

        @EventListener(ApplicationReadyEvent.class)
        private void test() throws JsonProcessingException {

        giveMeResponse();

//            System.out.println(giveMeResponse().getQuestionWithAnswers());

//        String r = sendGet(1,1);
//        Response response = gson.fromJson(r, Response.class);
//        System.out.println(response.getQuestionWithAnswers());


//            int quantity = 1;
//            int difficult = 1;
//            Gson gson = new Gson();
//
//            Response response = new Response();
//            System.out.println((gson.fromJson(sendGet(difficult,quantity), Response.class)).getQuestionWithAnswers());
//            response = ;
//
//            response.getData();


//            for (int i = 0; i < quantity; i++) {
//                String [] answers = response.getQuestionWithAnswers().get(i).getAnswers();
//                Question question = Question.builder()
//                        .question(response.getQuestionWithAnswers().get(i).getQuestion())
//                        .answer1(answers[0])
//                        .answer2(answers[1])
//                        .answer3(answers[2])
//                        .answer4(answers[3])
//                        .complexity(difficult)
//                        .hash(response.getQuestionWithAnswers().get(i).getQuestion().hashCode())
//                        .build();
//
//                questionService.saveOrUpdate(question);
//            }
        }
    }

