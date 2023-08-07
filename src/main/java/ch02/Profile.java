package ch02;

import java.util.HashMap;
import java.util.Map;

public class Profile {

    private final Map<String, Answer> answers = new HashMap<>();

    public Profile() {
    }

    public void add(Answer answer) {
        answers.put(answer.getQuestionText(), answer);
    }

    public boolean matches(Criteria criteria) {
        boolean kill = false;
        boolean anyMatches = false;
        for (Criterion criterion : criteria) {
            Answer answer = answers.get(
                    criterion.answer().getQuestionText());
            boolean match =
                    criterion.weight() == Weight.DontCare ||
                            answer.match(criterion.answer());

            if (!match && criterion.weight() == Weight.MustMatch) {
                kill = true;
            }
            anyMatches |= match;
        }
        if (kill)
            return false;
        return anyMatches;
    }

}
