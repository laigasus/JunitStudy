package ch01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ScoreCollectionTest {

    @Test
    @DisplayName("의도적인 pass")
    public void test() {
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("주어진 수들의 평균값이 올바르게 계산되었는가?")
    public void arithmeticMeanIsCorrect() {
        // given
        final int expected = 6;
        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> 5);
        collection.add(() -> 7);

        // when
        int actual = collection.arithmeticMean();

        // then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("평균값이 기대값과 다른가?")
    public void arithmeticMeanIsDifferent() {
        // given
        final int expected = 1;
        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> 5);
        collection.add(() -> 7);

        // when
        int actual = collection.arithmeticMean();

        // then
        assertNotEquals(expected, actual);
    }
}
