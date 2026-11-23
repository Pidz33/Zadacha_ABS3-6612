package com.example;
//Класс чисто для вывода сообщения в аннотациях BeforeAll и AfterAll
public class Information {
    public static void informationMessageStart() {
        System.out.println("Запуск тестов");
    }
    public static void informationMessageEnding() {
        System.out.println("Тесты завершены");
    }

}
