import com.example.Calculator;
import com.example.Information;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
//Allure запускать через allure serve allure-results
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTests {
    private Calculator calculator;

    @BeforeAll
    static void startMessage() {
        Information.informationMessageStart();
    }
    @AfterAll
    static void endingMessage() {
        Information.informationMessageEnding();
    }

    @BeforeEach
    void setup() {
        calculator = new Calculator();
        System.out.println("Создан объект Calculator");
    }

    @AfterEach
    void tearDown() {
        calculator = null;
        System.out.println("Тест завершен");
    }


    @Test
    @DisplayName("Проверка метода add")
    void addTest() {
        int actual = calculator.add(2, 3);
        assertEquals(5, actual, "Сумма 2 и 3 должна быть равна 5");
    }

    @DisplayName("Проверка метода subtract")
    @ParameterizedTest
    @CsvSource({"100, 20, 80", "-10, 10, -20", "500, 600, -100"})
    void subtractTest(int a, int b, int expected) {
        int actual = calculator.subtract(a, b);
        assertEquals(expected, actual, "Разность " + a + " и " + b + " должна быть равна " + expected);
    }

    static Stream<Arguments> dataForMultiplyTest() {
        return Stream.of(Arguments.of(20, 10, 200), Arguments.of(333, 666, 221778), Arguments.of(-153, 3, -459), Arguments.of(0, 10, 0));
    }

    @DisplayName("Проверка метода multiply")
    @ParameterizedTest
    @MethodSource("dataForMultiplyTest")
    void multiplyTest(int a, int b, int expected){
        int actual = calculator.multiply(a, b);
        assertEquals(expected, actual, "Результат умножения чисел " + a + " и " + b + " должен равняться " + expected);

    }
    @DisplayName("Проверка метода divide")
    @Test
    void divideTest() {
        int actual = calculator.divide(10, 5);
        assertEquals(2, actual);
    }
    @DisplayName("Проверка метода divide на выброс исключения при нулевом делителе")
    @Test
    void divideTestByZero () {

        assertThrows(IllegalArgumentException.class,
                () -> calculator.divide(10, 0));
    }
}
