package ch03;

import org.junit.jupiter.api.*;
import org.opentest4j.AssertionFailedError;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class AssertTest {

    private Account account;

    @BeforeEach // 상태변화를 발생하는 메서드도 고려하여 매 테스트마다 수행
    public void createAccount() {
        account = new Account("an account name");
    }

    @Test
    @DisplayName("계정에 현금 최초 입금시 잔액이 올랐는가?")
    public void hasPositiveBalance() {
        account.deposit(50);

        assertTrue(account.hasPositiveBalance());
    }

    @Test
    @DisplayName("계정에 현금 입금시 이전 전액과 추가된 금액의 합 액수가 일치하는가?")
    public void depositIncreasesBalance() {
        int initialBalance = account.getBalance();
        account.deposit(100);
        assertTrue(account.getBalance() > initialBalance);
        assertEquals(account.getBalance(), 100);
    }

    @Test
    @DisplayName("두개의 문자열 배열이 주어졌을 때, 내용물이 다른가?")
    public void comparesArraysFailing() {
        assertThrows(AssertionFailedError.class, () ->
                assertArrayEquals(new String[]{"a", "b", "c"}, new String[]{"a", "b"})
        );

    }

    @Test
    @SuppressWarnings("EqualsWithItself")
    @DisplayName("두개의 문자열 배열이 주어졌을 때, 내용물이 같은가?")
    public void comparesArraysPassing() {
        assertArrayEquals(new String[]{"a", "b"}, new String[]{"a", "b"});
    }

    @Test
    @SuppressWarnings("ConstantValue")
    @DisplayName("두개의 컬렉션이 주어졌을 때, 내용물이 다른가?")
    public void comparesCollectionsFailing() {
        var list1 = List.of("a");
        var list2 = List.of("a", "ab");

        // AssertionFailureBuilder 사용
        if (list1.equals(list2)) {
            throw AssertionFailureBuilder.assertionFailure()
                    .message("두 컬렉션의 값이 같습니다")
                    .expected(false)
                    .actual(true)
                    .build();
        }
    }

    @Test
    @DisplayName("두개의 컬렉션이 주어졌을 때, 내용물이 같은가?")
    public void comparesCollectionsPassing() {
        var list1 = List.of("a");
        var list2 = List.of("a");

        // 메시지만 임의로 변경
        assertEquals(list1, list2, "두 컬렉션의 값이 다릅니다");
    }

    @Test
    @Disabled
    @DisplayName("테스트명은 일관성 있게!")
    public void testWithWorthlessAssertionComment() {
        /*
            테스트코드는 개발자가 로직을 이해한다는 전제하에 화이트박스테스트(기초경로검사, 제어구조 검사)를 합니다.
            일관성 있는 이름으로 테스트를 문서화하세요
        */
        account.deposit(50);
        assertEquals(account.getBalance(), 50);
    }

    @Test
    @DisplayName("assertThrows 로 예상한 예외가 나왔을 때 통과하게 함")
    public void assertFailure() {
        assertThrows(AssertionFailedError.class, () ->
                assertTrue(account.getName().startsWith("xyz"))
        );
    }

    @Test
    @DisplayName("햄크레스트 메서드 종류 소개")
    public void variousMatcherTests() {
        final String INPUT_STRING = "my big fat acct";
        Account account = new Account(INPUT_STRING);
        assertEquals(account.getName(), INPUT_STRING);

/*      그냥 Matcher 없이 써봄 ㅇㅅㅇ
        assertThat(account.getName(), allOf(startsWith("my"), endsWith("acct")));
        assertThat(account.getName(), anyOf(startsWith("my"), endsWith("loot")));
        assertThat(account.getName(), not(equalTo("plunderings")));
        assertThat(account.getName(), is(not(nullValue())));
        assertThat(account.getName(), is(notNullValue()));
        assertThat(account.getName(), isA(String.class));
        assertThat(account.getName(), is(notNullValue())); // not helpful
        assertThat(account.getName(), equalTo("my big fat acct"));*/

        assertTrue(account.getName().startsWith("my") && account.getName().endsWith("acct"));
        assertTrue(account.getName().startsWith("my") || account.getName().endsWith("loot"));
        assertNotEquals("plunderings", account.getName());
        assertNotNull(account.getName());
        assertEquals(String.class, account.getName().getClass());
        assertEquals(INPUT_STRING, account.getName());
    }

    @Test
    @DisplayName("두 인스턴스 클래스가 동일한가?")
    public void sameInstance() {
        Account a = new Account("a");
        Account aPrime = new Account("a");

        //assertThat(a, not(org.hamcrest.CoreMatchers.sameInstance(aPrime)));
        assertNotSame(a, aPrime);
    }

    @Test
    @DisplayName("인스턴스(Acount)의 필드(name) 값이 비어있는가?")
    public void moreMatcherTests() {
        Account account = new Account(null);
        assertThat(account.getName(), is(nullValue()));
        //assertNull(account.getName());
    }

    @Test
    @SuppressWarnings("CommentedOutCode")
    @DisplayName("두 인스턴스 클래스가 동일한가?")
    public void items() {
        List<String> names = new ArrayList<>();
        names.add("Moe");
        names.add("Larry");
        names.add("Curly");

/*        // Junit5 only
        assertTrue(names.contains("Curly"));
        assertTrue(names.containsAll(List.of("Curly", "Moe")));
        assertTrue(names.stream().anyMatch(name -> name.endsWith("y")));
        assertTrue(names.stream().anyMatch(name -> name.endsWith("y") || name.startsWith("C")));
        assertFalse(names.stream().allMatch(name -> name.endsWith("y")));*/

        assertThat(names, hasItem("Curly"));
        assertThat(names, hasItems("Curly", "Moe"));
        assertThat(names, hasItem(endsWith("y")));
        assertThat(names, hasItems(endsWith("y"), startsWith("C"))); //warning!
        assertThat(names, not(everyItem(endsWith("y"))));
    }

    @Test
    @DisplayName("좌표값이 요청한 값의 오차범위에 해당하는가?(햄크레스트)")
    public void location() {
        Point point = new Point(4, 5);
        assertThat(point.x(), closeTo(3.6, 1));
        assertThat(point.y(), closeTo(5.1, 1));
    }

    @Test
    @Disabled
    @DisplayName("단순 assertions 비교")
    public void classicAssertions() {
        Account account = new Account("acct namex");
        assertEquals("acct name", account.getName());
    }

    @Test
    @DisplayName("사용자 예외클래스를 사용하여 테스트 진행 - throws")
    public void throwsWhenWithdrawingTooMuch() {
        Assertions.assertThrows(InsufficientFundsException.class, () ->
                account.withdraw(100)
        );
    }

    @Test
    @DisplayName("사용자 예외클래스를 사용하여 테스트 진행 - 옛날 방식의 try-catch")
    public void throwsWhenWithdrawingTooMuchTry() {
        try {
            account.withdraw(100);
            fail();
        } catch (InsufficientFundsException expected) {
            assertEquals(expected.getMessage(), "balance only 0");
        }
    }

    @Test
    @DisplayName("사용자 예외클래스를 사용하여 테스트 진행 - 그냥 throws 던져~")
    public void readsFromTestFile() throws IOException {
        String filename = "test.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write("test data");
        writer.close();
        // ...
    }

    @AfterEach
    public void deleteForReadsFromTestFile() {
        //noinspection ResultOfMethodCallIgnored
        new File("test.txt").delete();
    }

    @Test
    @SuppressWarnings("EmptyMethod")
    @Disabled("don't forget me!")
    public void somethingWeCannotHandleRightNow() {
        // ...
    }

    @Test
    @DisplayName("예외 Rule 정의하여 예상한 예외처리가 진행되었는지 체크")
    public void exceptionRule() {
        Exception exception = assertThrows(InsufficientFundsException.class, () -> account.withdraw(100));
        assertEquals("balance only 0", exception.getMessage());
    }

    @Test
    public void doubles() {
        assertEquals(9.7, 10.0 - 0.3, 0.005);
    }
}