package ru.gb.telegrambotgateway.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gb.telegrambotgateway.model.RatingDto;

@Service
public class RatingServiceImpl implements ru.gb.telegrambotgateway.service.inter.RatingService {

    @Value("${project.host}")
    private String host;

    @Value("${project.questionApi.port}")
    private String port;

    @Override
    public RatingDto getDailyRating(Long chatId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://" + host + ":" + port + "/rating/daily/" + chatId;
        ResponseEntity<RatingDto> responseEntity = restTemplate.getForEntity(url, RatingDto.class);
        return responseEntity.getBody();
    }

    @Override
    public RatingDto getMonthlyRating(Long chatId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://" + host + ":" + port + "/rating/monthly/" + chatId;
        ResponseEntity<RatingDto> responseEntity = restTemplate.getForEntity(url, RatingDto.class);
        return responseEntity.getBody();
    }

    @Override
    public RatingDto getWeeklyRating(Long chatId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://" + host + ":" + port + "/rating/weekly/" + chatId;
        ResponseEntity<RatingDto> responseEntity = restTemplate.getForEntity(url, RatingDto.class);
        return responseEntity.getBody();
    }

    @Override
    public RatingDto getTotalRating(Long chatId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://" + host + ":" + port + "/rating/total/" + chatId;
        ResponseEntity<RatingDto> responseEntity = restTemplate.getForEntity(url, RatingDto.class);
        return responseEntity.getBody();
    }

}
