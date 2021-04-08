package ru.job4j.solid.lsp.productstorage.model;

import java.time.LocalDateTime;

public class Cheese extends Food {
    public Cheese(String name, LocalDateTime createDate, LocalDateTime expiryDate, int price, int discount) {
        super(name, createDate, expiryDate, price, discount);
    }
}
