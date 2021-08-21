package ru.gb.questionapi;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.gb.questionapi.domain.Question;
import ru.gb.questionapi.parsing.Response;
import ru.gb.questionapi.services.QuestionService;


public class SendRequest {

    @Autowired
    private static Gson gson;
    @Autowired
    private static QuestionService questionService;



    public static String sendGet(int difficult, int quantity){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> result = restTemplate.exchange("https://engine.lifeis.porn/api/millionaire.php?qType={difficult}&count={quantity}", HttpMethod.GET, entity, String.class, difficult, quantity);

        System.out.println(result.getStatusCode());

        return result.getBody();
    }

    public static void takeNewQuestionWithAnswers(int difficult, int quantity) {

        String r = sendGet(difficult, quantity);
        Gson gson = new Gson();
        Response response = gson.fromJson(r, Response.class);

        for (int i = 0; i < quantity; i++) {
            String[] answers = response.getData().get(i).getAnswers();
            Question question = new Question();
            question.setQuestion(response.getData().get(i).getQuestion());
            question.setAnswer1(answers[0]);
            question.setAnswer2(answers[1]);
            question.setAnswer3(answers[2]);
            question.setAnswer4(answers[3]);
            question.setComplexity(difficult);
            question.setHash(answers[0].hashCode());
            System.out.println("Получил новый вопрос: " + question);
            System.out.println("Хэш для проверки: " + question.getHash());


            if (isQuestionInDB(question.getHash())){
                System.out.println("Такого хэша нет");

            } else System.out.println("Такой хэш есть");
        }
    }


    public static Response giveMeResponse(int difficult, int quantity){
        String r = sendGet(difficult, quantity);
        Gson gson = new Gson();
        return gson.fromJson(r, Response.class);
    }

    public static boolean isQuestionInDB(int hash){
        if (questionService.findByHash(hash) == null) return true;
        else return false;
    }
}
