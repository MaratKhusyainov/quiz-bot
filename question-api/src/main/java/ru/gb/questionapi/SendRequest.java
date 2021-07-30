package ru.gb.questionapi;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gb.questionapi.parsing.Response;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;

public class SendRequest {

    @Autowired
    private static Gson gson;

    public static String sendGet(int difficult, int quantity){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> result = restTemplate.exchange("https://engine.lifeis.porn/api/millionaire.php?qType={difficult}&count={quantity}", HttpMethod.GET, entity, String.class, difficult, quantity);

        System.out.println(result.getStatusCode());
//        System.out.println(result.getBody());

        return result.getBody();
    }


    public static void giveMeResponse(){

        String r = sendGet(1,1);
        System.out.println(r);
        Response response = gson.fromJson(r, Response.class);
        System.out.println(response);
    }

}
