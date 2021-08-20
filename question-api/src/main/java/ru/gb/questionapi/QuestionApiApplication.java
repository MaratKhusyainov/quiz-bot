package ru.gb.questionapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import javafx.util.converter.LocalDateTimeStringConverter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.internal.expression.function.CurrentTimestampFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;

import ru.gb.questionapi.domain.History;
import ru.gb.questionapi.domain.Question;
import ru.gb.questionapi.domain.User;
import ru.gb.questionapi.parsing.QuestionWithAnswers;
import ru.gb.questionapi.parsing.Response;

import ru.gb.questionapi.services.HistoryService;
import ru.gb.questionapi.services.QuestionService;
import ru.gb.questionapi.services.UserService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;



import static ru.gb.questionapi.SendRequest.sendGet;



//@SpringBootApplication(exclude = {JacksonAutoConfiguration.class })
@SpringBootApplication
@PropertySource("classpath:telegram-bot-secret.properties")
public class QuestionApiApplication {

//    @Autowired
//    private HistoryService historyService;
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private QuestionService questionService;



    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(QuestionApiApplication.class, args);
    }
//
//    @EventListener(ApplicationReadyEvent.class)
//    private void test() throws JsonProcessingException {



//        int difficult = 2;
//        int quantity = 3;
//        String r = sendGet(difficult,quantity);
//
//        Gson gson = new Gson();
//        Response response = gson.fromJson(r, Response.class);
//
////        String [] answers = new String[4];
//
//
//        for (int i = 0; i < quantity; i++) {
//            String [] answers = response.getData().get(i).getAnswers();
//            Question question = new Question();
//            question.setQuestion(response.getData().get(i).getQuestion());
//            question.setAnswers(response.getData().get(i).getAnswers());
//            question.setAnswer1(answers[0]);
//            question.setAnswer2(answers[1]);
//            question.setAnswer3(answers[2]);
//            question.setAnswer4(answers[3]);
//            question.setComplexity(difficult);
//            question.setHash(answers[0].hashCode());
//
//        questionService.saveOrUpdate(question);
//
//        }

//        User user = userService.findById(1L);
//        System.out.println(user.getName());
//
//
//        History history = new History(1L, user,  1L, true, LocalDateTime.now());
//
//        historyService.saveOrUpdate(history);
//    }
}