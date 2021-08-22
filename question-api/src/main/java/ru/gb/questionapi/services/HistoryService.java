package ru.gb.questionapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.questionapi.dao.HistoryRepository;
import ru.gb.questionapi.domain.History;
import ru.gb.questionapi.domain.User;
import ru.gb.questionapi.dto.HistoryDto;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private UserService userService;

    public void setHistoryRepository(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public void saveOrUpdate(HistoryDto historyDto) {
        System.out.println(historyDto);
        User user = userService.findByChatId(historyDto.getChatId());
        History history = History.builder()
                .user(user)
                .questionId(historyDto.getQuestionId())
                .isCorrect(historyDto.getIsCorrect())
                .build();
        historyRepository.save(history);
    }
}
