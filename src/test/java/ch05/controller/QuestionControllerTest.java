package ch05.controller;

import ch05.domain.Question;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

//@Disabled("데이터 넣고 테스트 해야 함!")
public class QuestionControllerTest {

    private static QuestionController controller;

    @BeforeEach
    public void create() {
        controller = new QuestionController();
        controller.deleteAll();
    }

    @AfterEach
    public void cleanup() {
        controller.deleteAll();
    }

    @Test
    public void findsPersistedQuestionById() {
        int id = controller.addBooleanQuestion("question text");

        Question question = controller.find(id);

        assertThat(question.getText(), equalTo("question text"));
    }

    @Test
    public void questionAnswersDateAdded() {
        Instant now = new Date().toInstant();
        controller.setClock(Clock.fixed(now, ZoneId.of("America/Denver")));
        int id = controller.addBooleanQuestion("text");

        Question question = controller.find(id);

        assertThat(question.getCreateTimestamp(), equalTo(now));
    }

    @Test
    @Disabled("Cannot delete or update a parent row")
    public void answersMultiplePersistedQuestions() {
        controller.addBooleanQuestion("q1");
        controller.addBooleanQuestion("q2");
        controller.addPercentileQuestion("q3", new String[]{"a1", "a2"});

        List<Question> questions = controller.getAll();

        assertThat(questions.stream()
                        .map(Question::getText)
                        .collect(Collectors.toList()),
                equalTo(Arrays.asList("q1", "q2", "q3")));
    }

    @Test
    @Disabled("Cannot delete or update a parent row")
    public void findsMatchingEntries() {
        controller.addBooleanQuestion("alpha 1");
        controller.addBooleanQuestion("alpha 2");
        controller.addBooleanQuestion("beta 1");

        List<Question> questions = controller.findWithMatchingText("alpha");

        assertThat(questions.stream()
                        .map(Question::getText)
                        .collect(Collectors.toList()),
                equalTo(Arrays.asList("alpha 1", "alpha 2")));
    }
}
