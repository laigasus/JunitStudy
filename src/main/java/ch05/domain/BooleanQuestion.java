package ch05.domain;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.io.Serial;
import java.util.Arrays;
import java.util.List;

@Entity
@DiscriminatorValue(value = "boolean")
public class BooleanQuestion extends Question {
    @Serial
    private static final long serialVersionUID = 1L;

    public BooleanQuestion() {
    }

    public BooleanQuestion(String text) {
        super(text);
    }

    @SuppressWarnings({"unused", "JpaAttributeTypeInspection"})
    @Override
    public List<String> getAnswerChoices() {
        return Arrays.asList("No", "Yes");
    }

    @SuppressWarnings("unused")
    @Override
    public boolean match(int expected, int actual) {
        return expected == actual;
    }
}
