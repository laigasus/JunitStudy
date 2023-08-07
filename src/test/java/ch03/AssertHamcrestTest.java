package ch03;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings({"ConstantConditions", "RedundantSuppression"})
public class AssertHamcrestTest {
    @Test
    @Disabled // @Ignore(Junit4) -> @Disabled(Junit5)
    @DisplayName("double 연산이 예측값과 같을까? (Disabled)")
    public void assertDoublesEqual() {
        assertEquals(2.32 * 3, 6.96);
    }

    @Test
    @DisplayName("double 연산이 오차 범주 내 들까?(햄크레스트 미사용)")
    public void assertWithTolerance() {
        assertTrue(Math.abs((2.32 * 3) - 6.96) < 0.0005);
    }

    @Test
    @DisplayName("double 연산이 오차 범주 내 들까?(햄크레스트 사용)")
    public void assertDoublesCloseTo() {
        assertThat(2.32 * 3, closeTo(6.96, 0.0005));
    }
}