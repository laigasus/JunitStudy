package ch03;

import org.junit.jupiter.api.*;

@SuppressWarnings("EmptyMethod")
public class AssertMoreTest {
    @BeforeAll
    @DisplayName("@BeforeAll > 초기 테스트 진입 전 실행")
    public static void initializeSomethingReallyExpensive() {
        // ...
    }

    @AfterAll
    @DisplayName("@AfterAll > 모든 테스트 수행 후 실행")
    public static void cleanUpSomethingReallyExpensive() {
        // ...
    }

    @BeforeEach
    @DisplayName("@BeforeEach > 각 테스트 진입 전 실행")
    public void createAccount() {
        // ...
    }

    @AfterEach
    @DisplayName("@AfterAll > 각 테스트 수행 후 실행")
    public void closeConnections() {
        // ...
    }

    @Test
    public void depositIncreasesBalance() {
        // ...
    }

    @Test
    public void hasPositiveBalance() {
        // ...
    }
}
