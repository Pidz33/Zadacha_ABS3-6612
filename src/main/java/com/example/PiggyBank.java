package com.example;

public class PiggyBank {
    private int balance;

    public int deposit(int amount) {// Метод для пополнения счета
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма для пополнения должна быть положительной");
        }
        return balance += amount;
    }
    public int withdraw(int amount) {// Метод для снятия денег со счёта
        if (balance < amount) {
            throw new IllegalArgumentException("Недостаточно средств");
        } if (amount <= 0) {
            throw new IllegalArgumentException("Сумма для снятия должна быть больше нуля и положительной");
        }
        else {
            return balance -= amount;
        }
    }
    public int getBalance() {// Метод для получения баланса
        return balance;
    }
}
