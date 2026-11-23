package com.example;

public class DiscountCalculator {

    public int apply(int price, int percent) {
        if (price <= 0) {
            throw new IllegalArgumentException("Цена не должна быть нулевой или отрицательнй");
        }

        if (percent < 0 || percent >= 100) {
            throw new IllegalArgumentException("Скидка не может быть меньше 0 или равна 100%");
        }

        return price - ((price * percent) / 100);
        //цена 1000 - (1000 * 10 /) 100) = 900
        //добавить проверку, что цена тоже не может быть нулевой
    }
}
