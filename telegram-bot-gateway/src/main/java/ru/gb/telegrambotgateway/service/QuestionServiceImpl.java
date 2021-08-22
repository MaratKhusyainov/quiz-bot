package ru.gb.telegrambotgateway.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gb.telegrambotgateway.model.AnswerDto;
import ru.gb.telegrambotgateway.model.QuestionDto;
import ru.gb.telegrambotgateway.repository.QuestionRepository;

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
        /*List<Question> questions = new ArrayList<>();
        questions.add(new Question(1L, "a", "a1", "a2", "a3", "a4"));
        questions.add(new Question(2L, "b", "b1", "b2", "b3", "b4"));
        questions.add(new Question(3L, "c", "c1", "c2", "c3", "c4"));
        questions.add(new Question(3L, "d", "d1", "d2", "d3", "d4"));

        Question question = getByChatId(chatId);
        if (question == null) {
            question = questions.get((int) (Math.random() * 4));
        }*/

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://" + host + ":" + port + "/question/" + chatId;
        ResponseEntity<QuestionDto> responseEntity = restTemplate.getForEntity(url, QuestionDto.class);
        save(chatId, responseEntity.getBody());
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
