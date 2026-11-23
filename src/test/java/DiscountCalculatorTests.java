import com.example.DiscountCalculator;
import com.example.Information;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountCalculatorTests {
    private DiscountCalculator discountCalculator;

    @BeforeAll
    static void startMessage() {
        Information.informationMessageStart();
    }
    @AfterAll
    static void endingMessage() {
        Information.informationMessageEnding();
    }

    @BeforeEach
    void setup(TestInfo testInfo) {
        discountCalculator = new DiscountCalculator();
        System.out.println("Создан объект DiscountCalculator" + testInfo.getDisplayName());
    }

    @AfterEach
    void tearDown() {
        discountCalculator = null;
        System.out.println("Тест завершен");
    }
    @DisplayName("Проверка метода apply с положительными значениями")
    @ParameterizedTest
    @CsvSource({"100, 10, 90", "100, 0, 100"})
    void applyTest_Positive(int price, int discount, int expected) {
        int actual = discountCalculator.apply(price, discount);
        assertEquals(expected, actual, "Скидка должна рассчитываться по формуле: price - (price * percent / 100) и быть " + expected);
    }
    @DisplayName("Проверка метода apply с невалидными значениями")
    @ParameterizedTest
    @CsvSource({"100, -10", "100, 101",  "100, 100", "0, 10", "-100, 10"})
    void applyTest_Negative(int price, int discount) {
        assertThrows(IllegalArgumentException.class,
                ()-> discountCalculator.apply(price, discount), "Для цены: " + price + " и скидки: " + discount + " должно бросаться исключение");
    }
    @DisplayName("Тест на с утверждением assertAll")
    @ParameterizedTest
    @CsvSource({"100, 10, 90", "100, 0, 100"})
    void applyTest_Positive_Repeat(int price, int discount, int expected) {
        int actual = discountCalculator.apply(price, discount);
        assertAll(
                ()-> assertEquals(expected, actual),
                ()-> assertEquals(expected, actual));
    }

}
