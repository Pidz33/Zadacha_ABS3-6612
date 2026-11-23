import com.example.TrafficLight;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class TrafficLightTests {
    private TrafficLight trafficLight;

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        trafficLight = new TrafficLight();
        System.out.println("Запуск теста: " + testInfo.getDisplayName());
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        System.out.println("Завершение теста: " + testInfo.getDisplayName());
    }

    @Nested
    @DisplayName("Тестирование метода next")
    class validColorCheckTest {

        @ParameterizedTest (name = "{0} → {1}")
        @CsvSource({"red,    green",
                "green,  yellow",
                "yellow, red" })
        @DisplayName("Проверка, что цвета последовательно меняются")
        void testNext(String current, String expected) {
            assertEquals(expected, trafficLight.next(current));

        }
        @Test
        @DisplayName("Полный цикл red → green → yellow → red")
        void testFullCycle() {
            String color = "red";
            color = trafficLight.next(color);
            color = trafficLight.next(color);
            color = trafficLight.next(color);
            assertEquals("red", color);
        }
    }
    @Nested
    @DisplayName("Обработка null")
    class NullHandling {

        @ParameterizedTest(name = "null → исключение")
        @NullSource
        @DisplayName("null бросает IllegalArgumentException")
        void shouldThrowOnNull(String current) {
            IllegalArgumentException e = assertThrows(
                    IllegalArgumentException.class,
                    () -> trafficLight.next(current));

            assertTrue(e.getMessage().contains("null"),
                    "сообщение должно упоминать null, было: " + e.getMessage());
        }
    }
    @Nested
    @DisplayName("Невалидные значения")
    class UnknownValues {

        @ParameterizedTest(name = "[{index}] \"{0}\" → исключение")
        @ValueSource(strings = {
                "blue", "RED", "Red", "GREEN", "Yellow",  // другой регистр
                " red", "red ", " red ",                   // пробелы по краям
                "", " ", "\t", "\n",                       // пустые / пробельные
                "redgreenyellow", "der", "red,green"               // невалидные комбинации цветов
        })
        @DisplayName("Неизвестный цвет бросает исключение")
        void shouldThrowOnUnknownColor(String current) {
            IllegalArgumentException ex = assertThrows(
                    IllegalArgumentException.class,
                    () -> trafficLight.next(current));

            assertTrue(ex.getMessage().contains(current),
                    "сообщение должно содержать исходное значение, было: " + ex.getMessage());
        }

        @Test
        @DisplayName("Проверка, что регистр влияет на результат")
        void shouldBeCaseSensitive() {
            assertThrows(IllegalArgumentException.class,
                    () -> trafficLight.next("RED"));
            assertEquals("green", trafficLight.next("red"));
        }

        @Test
        @DisplayName("Проверка, что пробелы влияют на результат")
        void shouldNotTrimInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> trafficLight.next(" red"));
            assertEquals("green", trafficLight.next("red"));
        }
    }

}
