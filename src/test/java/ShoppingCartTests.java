import com.example.ShoppingCart;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShoppingCartTests {
    private ShoppingCart shoppingCart;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        shoppingCart = new ShoppingCart();
        System.out.println("Создаем корзину для теста: " + testInfo.getDisplayName());
    }
    @AfterEach
    void tearDown(TestInfo testInfo) {
        shoppingCart = null;
        System.out.println("Корзина очищена после теста: " + testInfo.getDisplayName());
    }

    @Nested
    @DisplayName("Проверка метода addItem()")
    class AddItemTests {

        @Test
        @DisplayName("Добавление товара в корзину")
        void addItemTest() {
            shoppingCart.addItem("Яблоко", 10, 10);
            assertEquals(100, shoppingCart.getTotal());
            assertEquals(10, shoppingCart.getItemCount());
        }
        @Test
        @DisplayName("Проверка, что количество товара увеличивается при добавлении")
        void addItemTestIncreasingQty(TestReporter testReporter) {
            shoppingCart.addItem("Яблоко", 10, 1);
            shoppingCart.addItem("Яблоко", 10, 3);
            assertEquals(40, shoppingCart.getTotal());
            assertEquals(4, shoppingCart.getItemCount());

        }
        @Test
        @DisplayName("Проверка, что цена товара не меняется")
        void addItemTestPriceNotChange(TestReporter testReporter) {
            shoppingCart.addItem("Яблоко", 10, 1);
            shoppingCart.addItem("Яблоко", 100, 1);
            assertEquals(20, shoppingCart.getTotal());
            assertEquals(2, shoppingCart.getItemCount());
        }
        @ParameterizedTest
        @ValueSource(ints = {0, -1, -100})
        @DisplayName("Проверка, что выбрасывается исключение при невалидном количестве")
        void addItemTestQtyLessThanZero(int qty) {
            assertThrows(IllegalArgumentException.class, () ->
                    shoppingCart.addItem("Яблоко", 10, qty));
            assertEquals(0, shoppingCart.getTotal());
            assertEquals(0, shoppingCart.getItemCount());
        }
        @ParameterizedTest
        @DisplayName("Проверка, что выбрасывается исключение при невалидной цене")
        @ValueSource(ints = {0, -1, -100})
        void addItemTestPriceLessThanZero(int price) {
            assertThrows(IllegalArgumentException.class, () ->
                    shoppingCart.addItem("Яблоко", price, 1));
            assertEquals(0, shoppingCart.getTotal());
        }
    }
    @Nested
    @DisplayName("Тесты удаления товара из корзины методом removeItem")
    class RemoveItemTests {


        @Test
        @DisplayName("Удаление существующего товара из корзины")
        void removeItemTest() {
            shoppingCart.addItem("Яблоко", 10, 1);
            shoppingCart.removeItem("Яблоко");
            assertEquals(0, shoppingCart.getTotal());
            assertEquals(0, shoppingCart.getItemCount());
        }
    }
    @Test
    @DisplayName("Проверка, что при удалении несуществующего товара выбрасывается исключение")
    void removeItemTestThrowsException() {
        shoppingCart.addItem("Яблоко", 10, 1);
        assertThrows(IllegalArgumentException.class, () ->
                shoppingCart.removeItem("Апельсин"));
        assertEquals(10, shoppingCart.getTotal());
    }
    @ParameterizedTest
    @DisplayName("Проверка, что при невалидном имени выбрасывается исключение")
    @ValueSource(strings = {"", " ", "  "})
    @NullAndEmptySource
    void addItemTestEmptyName(String name) {
        assertThrows(IllegalArgumentException.class, () ->
                shoppingCart.addItem(name, 10, 1));
    }

    @Nested
    @DisplayName("Тесты на проверку метода getTotal")
    class GetTotalTests {

        @Test
        @DisplayName("Проверка, что getTotal возвращает 0, если в корзине нет товаров")
        void getTotalTestEmptyCart() {
            assertEquals(0, shoppingCart.getTotal());
        }

        @Test
        @DisplayName("Проверка, что getTotal возвращает сумму всех товаров")
        void getTotalPriceTest() {
            shoppingCart.addItem("Яблоко", 10, 2);
            shoppingCart.addItem("Апельсин", 50, 3);
            shoppingCart.addItem("Банан", 30, 1);

            assertEquals(200, shoppingCart.getTotal());

        }

        @Nested
        @DisplayName("Тесты на проверку метода getItemCount")
        class GetItemCountTests {

            @Test
            @DisplayName("Проверка, что getItemCount возвращает 0, если в корзине нет товаров")
            void getItemCountTestEmptyCart() {
                assertEquals(0, shoppingCart.getItemCount());
            }

            @Test
            @DisplayName("Проверка, что getItemCount возвращает количество товаров")
            void getItemCountTest() {
                shoppingCart.addItem("Яблоко", 10, 2);
                shoppingCart.addItem("Апельсин", 50, 3);
                assertEquals(5, shoppingCart.getItemCount());
            }
        }
    }
}
