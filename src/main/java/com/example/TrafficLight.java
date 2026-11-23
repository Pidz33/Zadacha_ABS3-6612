package com.example;

public class TrafficLight {

    public String next(String current) {
        if(current == null) {
            throw new IllegalArgumentException("Текущий цвет не может быть null");
        }
        switch (current) {
            case "red":
                return "green";
            case "green":
                return "yellow";
            case "yellow":
                return "red";
            default:
                throw new IllegalArgumentException("Неизвестный цвет: " + current);
        }
    }
}

