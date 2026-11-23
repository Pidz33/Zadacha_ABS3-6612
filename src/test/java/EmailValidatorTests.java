import com.example.EmailValidator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.junit.jupiter.api.Assertions.*;

public class EmailValidatorTests {
    private EmailValidator emailValidator;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        emailValidator = new EmailValidator();
        System.out.println("Запуск теста: " + testInfo.getDisplayName());
    }
    @AfterEach
    void tearDown() {
        emailValidator = null;
        System.out.println("Тест завершен");
    }


    @DisplayName("Проверка валидации email методом isValid")
    @ParameterizedTest(name = "[{index}] email={0} → ожидается {1}")
    @CsvSource(
            value = {
                    // валидные
                    "example@example.com,            true",
                    "Nagibator228@yandex.ru,         true",
                    // невалидные
                    "example@example,                false",
                    "Nagibator228@,                  false",
                    "test test@test.com,             false",
                    "testtest.com,                   false",
                    "test,                           false",
                    // null и пустые
                    "NULL,                           false",
                    "'',                             false",
                    "' ',                            false"
            },
            nullValues = "NULL"
    )
    void isValidTest(String email, boolean expected) {
        assertEquals(expected, emailValidator.isValid(email));
    }

}
