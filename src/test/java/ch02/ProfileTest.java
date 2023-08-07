package ch02;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfileTest {
    private static Profile profile;
    private static BooleanQuestion question;
    private static Criteria criteria;

    // @Before(Junit4) -> BeforeEach(Junit5), @BeforeClass(JUnit4) -> @BeforeAll(JUnit5)
    @BeforeAll
    public static void create() {
        profile = new Profile();
        question = new BooleanQuestion(1, "Got bonuses?");
        criteria = new Criteria();
    }

    @Test
    public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
        // given
        profile.add(new Answer(question, Bool.FALSE));
        criteria.add(
                new Criterion(new Answer(question, Bool.TRUE), Weight.MustMatch));
        // when
        boolean matches = profile.matches(criteria);

        // then
        assertFalse(matches);
    }

    @Test
    public void matchAnswersTrueForAnyDontCareCriteria() {
        // given
        profile.add(new Answer(question, Bool.FALSE));
        criteria.add(
                new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare));

        // when
        boolean matches = profile.matches(criteria);

        // then
        assertTrue(matches);
    }
}