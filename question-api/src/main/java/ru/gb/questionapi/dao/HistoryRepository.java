package ru.gb.questionapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.gb.questionapi.domain.History;
import ru.gb.questionapi.domain.Question;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    String queryHowManyQuestionsUserGetToday =
                    "SELECT COUNT(*) " +
                    "FROM quiz_bot.history AS h " +
                    "WHERE h.user_id = (SELECT id " +
                                        "FROM quiz_bot.user " +
                                        "WHERE chat_id = :chatId) " +
                    "AND date > 'today' ;";
    @Query(value = queryHowManyQuestionsUserGetToday, nativeQuery = true)
    Integer howManyQuestionsUserGetToday(@Param("chatId") Long id);
}
