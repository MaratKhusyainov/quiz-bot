package ru.gb.questionapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.questionapi.dao.HistoryRepository;
import ru.gb.questionapi.domain.History;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    public void setHistoryRepository(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public void saveOrUpdate(History history) {
        historyRepository.save(history);
    }

//    public Question findById(Long id) {
//        return questionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException("Can't found question with id = " + id));
//    }


}
