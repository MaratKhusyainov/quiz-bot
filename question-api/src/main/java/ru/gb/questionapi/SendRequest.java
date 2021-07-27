package ru.gb.questionapi;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SendRequest {

    public static String sendGet(int difficult, int quantity){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> result = restTemplate.exchange("https://engine.lifeis.porn/api/millionaire.php?qType={difficult}&count={quantity}", HttpMethod.GET, entity, String.class, difficult, quantity);

        System.out.println(result.getStatusCode());

        return result.getBody();
    }

}
