package com.example;

public class Calculator {

    public int add(int a, int b) {//сложение
        return a + b;
    }

    public int subtract(int a, int b) {//вычитание
        return a - b;
    }

    public int multiply(int a, int b) {//умножение
        return a * b;
    }

    public int divide(int a, int b) {//деление
        if (b == 0) {
            throw new IllegalArgumentException("Делитель не может быть равен 0");
        }
        return a / b;
    }

}
