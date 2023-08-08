package ch05.domain;

import jakarta.persistence.*;

import java.io.Serial;
import java.util.Arrays;
import java.util.List;

@Entity
@DiscriminatorValue(value = "percentile")
public class PercentileQuestion extends Question {
    @Serial
    private static final long serialVersionUID = 1L;

    @ElementCollection
    @CollectionTable(name = "AnswerChoice",
            joinColumns = @JoinColumn(name = "question_id"))
    private List<String> answerChoices;

    public PercentileQuestion() {
    }

    public PercentileQuestion(String text, String[] answerChoices) {
        super(text);
        this.answerChoices = Arrays.asList(answerChoices);
    }

    @SuppressWarnings("unused")
    public List<String> getAnswerChoices() {
        return answerChoices;
    }

    @SuppressWarnings("unused")
    public void setAnswerChoices(List<String> answerChoices) {
        this.answerChoices = answerChoices;
    }

    @SuppressWarnings("unused")
    @Override
    public boolean match(int expected, int actual) {
        return expected <= actual;
    }
}