package ru.gb.questionapi.services;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gb.questionapi.dao.HistoryRepository;
import ru.gb.questionapi.dao.QuestionRepository;
import ru.gb.questionapi.domain.Question;
import ru.gb.questionapi.dto.QuestionDto;
import ru.gb.questionapi.exceptions.QuestionNotFoundException;
import ru.gb.questionapi.parsing.Response;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private static Gson gson;

    private final int QUANTITY_QUESTIONS = 5;// количество запрашиваемых вопросов за одни раз - ограничено API ресурса
    private final String APIKEY = "431c885cbba306fb1eb5974b0"; // apikey, который выдается ресурсом
    private final int MAXIMUM_QUESTIONS_PER_DAY = 20;

    public int chooseDifficultQuestionsMode(){
        int easy = 1;
        int hard = 3;
        return (easy + (int) (Math.random() * hard));
    }

    public void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void setHistoryRepository(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public void saveOrUpdate(Question question) {
        questionRepository.save(question);
    }

    public Question findById(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException("Can't found question with id = " + id));
    }

    public Question findByHash(int hash){
        return questionRepository.findByHash(hash);
    }

    public QuestionDto findNewQuestion(Long chatId) {
        Question newQuestion = questionRepository.findOneNewQuestion(chatId);
        Integer howManyQuestionsUserGetToday = historyRepository.howManyQuestionsUserGetToday(chatId);

        if (howManyQuestionsUserGetToday <= MAXIMUM_QUESTIONS_PER_DAY) {
            while (newQuestion == null) {
                getAndSaveUniqueNewQuestionsWithAnswersInDB(chooseDifficultQuestionsMode(), QUANTITY_QUESTIONS);
                newQuestion = questionRepository.findOneNewQuestion(chatId);
            }

            String[] answers = new String[]{newQuestion.getAnswer1(), newQuestion.getAnswer2(), newQuestion.getAnswer3(), newQuestion.getAnswer4()};
            return QuestionDto.builder()
                    .questionId(newQuestion.getId())
                    .question(newQuestion.getQuestion())
                    .answers(answers)
                    .canGetQuestion(true)
                    .build();
        } else {
            return QuestionDto.builder()
                    .questionId(null)
                    .question(null)
                    .answers(null)
                    .canGetQuestion(false)
                    .build();
        }
    }

    public void getAndSaveUniqueNewQuestionsWithAnswersInDB ( int difficult, int quantity){
        int savedQuestionCounter = 0;
        String r = sendGet(difficult, quantity, APIKEY);
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

            if (isQuestionInDB(question.getHash())) {
                questionRepository.save(question);
                savedQuestionCounter++;
            }
        }
    }

    public boolean isQuestionInDB ( int hash){
        return questionRepository.findByHash(hash) == null;
    }

    public String sendGet ( int difficult, int quantity, String apikey){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> result = restTemplate.exchange("https://engine.lifeis.porn/api/millionaire.php?qType={difficult}&count={quantity}&apikey={apikey}", HttpMethod.GET, entity, String.class, difficult, quantity, apikey);
        System.out.println(result.getStatusCode());
        return result.getBody();
    }

}
