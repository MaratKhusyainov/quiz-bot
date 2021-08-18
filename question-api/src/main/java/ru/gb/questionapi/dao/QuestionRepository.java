package ru.gb.questionapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.gb.questionapi.domain.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    String queryFindOnlyOneNoUseQuestion =
                "SELECT q.id, q.question, q.answer1, q.answer2, q.answer3, q.answer4, q.complexity, q.hash " +
                "FROM quiz_bot.question as q LEFT JOIN " +

                "(SELECT q.id " +
                "FROM quiz_bot.question AS q LEFT OUTER JOIN quiz_bot.history AS h " +
                "ON q.id = h.question_id " +
                "WHERE h.user_id = (SELECT id " +
                                    "FROM quiz_bot.user " +
                                    "WHERE chat_id = :chatId)) AS result " +
                "ON q.id = result.id " +

                "WHERE result.id IS NULL " +
                "LIMIT 1;";

    @Query(value = queryFindOnlyOneNoUseQuestion, nativeQuery = true)
    Question findOneNewQuestion(@Param("chatId") Long id);
}
