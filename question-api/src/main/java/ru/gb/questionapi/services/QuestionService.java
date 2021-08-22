package ru.gb.questionapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.questionapi.dao.QuestionRepository;
import ru.gb.questionapi.domain.Question;
import ru.gb.questionapi.dto.QuestionDto;
import ru.gb.questionapi.exceptions.QuestionNotFoundException;

import static ru.gb.questionapi.SendRequest.*;


@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
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

    public QuestionDto findNewQuestion(Long chatId){
        Question newQuestion = questionRepository.findOneNewQuestion(chatId);
        if (newQuestion == null) {
            System.out.println("Вопросы кончились, запрашиваю новый вопрос");
            takeNewQuestionWithAnswers(1,1);
        }

        String [] answers = new String [] {newQuestion.getAnswer1(), newQuestion.getAnswer2(), newQuestion.getAnswer3(), newQuestion.getAnswer4()};
        QuestionDto questionDto = QuestionDto.builder()
                .questionId(newQuestion.getId())
                .question(newQuestion.getQuestion())
                .answers(answers)
                .build();
        return questionDto;
    }
}
