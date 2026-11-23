package com.example;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    // в мапе храним название и товар
    private final Map<String, Item> items = new HashMap<>();

    // Внутренний класс для представления товара в корзине
    private static class Item {
        String name;
        int price;
        int qty;

        Item(String name, int price, int qty) {
            this.name = name;
            this.price = price;
            this.qty = qty;
        }
    }

    public void addItem(String name, int price, int qty) {
        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название не может быть пустым");
        }
        if (qty <= 0) {
            throw new IllegalArgumentException("Количество должно быть больше 0");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной или нулевой");
        }

        if (items.containsKey(name)) {
            // Если товар уже есть, увеличиваем его количество
            Item existingItem = items.get(name);
            existingItem.qty += qty;

        } else {
            // Иначе добавляем новый
            items.put(name, new Item(name, price, qty));
        }
    }

    public void removeItem(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название товара не может быть пустым");
        }
        if (!items.containsKey(name)) {
            throw new IllegalArgumentException("Товар не найден " + name);
        }
        items.remove(name); // Удаляет товар, если он есть. Если нет - ничего не делает
    }

    public int getTotal() {
        int total = 0;
        for (Item item : items.values()) {
            total += item.price * item.qty;
        }
        return total;
    }

    public int getItemCount() {
        int count = 0;
        for (Item item : items.values()) {
            count += item.qty;
        }
        return count;
    }
}