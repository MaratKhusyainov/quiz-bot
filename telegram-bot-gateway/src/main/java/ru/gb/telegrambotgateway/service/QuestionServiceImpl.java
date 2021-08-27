package ru.gb.telegrambotgateway.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gb.telegrambotgateway.model.AnswerDto;
import ru.gb.telegrambotgateway.model.QuestionDto;
import ru.gb.telegrambotgateway.repository.inter.QuestionRepository;
import ru.gb.telegrambotgateway.service.inter.QuestionService;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Value("${project.host}")
    private String host;

    @Value("${project.questionApi.port}")
    private String port;

    @Override
    public QuestionDto getQuestion(Long chatId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://" + host + ":" + port + "/question/" + chatId;
        ResponseEntity<QuestionDto> responseEntity = restTemplate.getForEntity(url, QuestionDto.class);
        if (responseEntity.getBody() != null && responseEntity.getBody().isCanGetQuestion()) {
            save(chatId, responseEntity.getBody());
        }
        return responseEntity.getBody();
    }

    @Override
    public void answer(Long chatId, QuestionDto question, boolean isCorrect) {
        delete(chatId);

        AnswerDto answerDto = new AnswerDto(chatId, question.getQuestionId(), isCorrect);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://" + host + ":" + port + "/history";
        restTemplate.postForEntity(url, answerDto, Integer.class);
    }

    @Override
    public QuestionDto getByChatId(Long chatId) {
        return questionRepository.getByChatId(chatId);
    }

    @Override
    public void save(Long chatId, QuestionDto questionDto) {
        questionRepository.save(chatId, questionDto);
    }

    @Override
    public void delete(Long chatId) {
        questionRepository.delete(chatId);
    }

}
