import com.example.PiggyBank;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PiggyBankTests {
    private PiggyBank piggyBank;

    @BeforeEach
    void setup(TestInfo testInfo) {
        piggyBank = new PiggyBank();
        System.out.println("Запуск теста: " + testInfo.getDisplayName());
    }
    @AfterEach
    void tearDown(TestInfo testInfo) {
        piggyBank = null;
        System.out.println("Тест завершен: " + testInfo.getDisplayName());
    }

    @Test
    @DisplayName("Тестирование метода deposit с положительным значением")
    void depositTest_Positive(TestReporter testReporter) {
        int actual = piggyBank.deposit(100);
        assertEquals(100, actual, "Сумма должна быть: " + piggyBank.getBalance());
        testReporter.publishEntry("Сумма в банке после пополнения: " + piggyBank.getBalance());
    }

    @Test
    @DisplayName("Тестирование метода deposit с отрицательным значением")
    void depositTest_Negative(TestReporter testReporter) {
        assertThrows(IllegalArgumentException.class, () -> piggyBank.deposit(-100));
        testReporter.publishEntry("Сумма в банке: " + piggyBank.getBalance());
    }

    @Test
    @DisplayName("Тестирование метода withdraw с положительным значением")
    void withdrawTest_Positive(TestReporter testReporter) {
        piggyBank.deposit(200);
        int actual = piggyBank.withdraw(100);
        assertEquals(100, actual, "Сумма должна быть: " + piggyBank.getBalance());
        testReporter.publishEntry("Сумма на счете после снятия: " + piggyBank.getBalance());
    }
    @Test
    @DisplayName("Проверка, что выбросится исключение при попытке снять больше, чем есть на счете")
    void withdrawTest_Negative() {
        piggyBank.deposit(100);
        assertThrows(IllegalArgumentException.class,
                () -> piggyBank.withdraw(200));
    }
    @ParameterizedTest
    @DisplayName("Проверка, что выбрасывается исключение при попытке снять ноль или отрицательное значение")
    @ValueSource(ints = {0, -1, -100})
    void withdrawTest_Negative_ZeroOrNegative(int amount) {
        assertThrows(IllegalArgumentException.class, () -> piggyBank.withdraw(amount));
    }
}
