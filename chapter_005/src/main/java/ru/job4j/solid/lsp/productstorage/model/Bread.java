package ru.job4j.solid.lsp.productstorage.model;

import java.time.LocalDateTime;

public class Bread extends Food {
    public Bread(String name, LocalDateTime createDate, LocalDateTime expiryDate, int price, int discount) {
        super(name, createDate, expiryDate, price, discount);
    }
}
