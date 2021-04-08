package ru.job4j.solid.lsp.productstorage.model;

import java.time.LocalDateTime;

public class Sausage extends Food {
    public Sausage(String name, LocalDateTime createDate, LocalDateTime expiryDate, int price, int discount) {
        super(name, createDate, expiryDate, price, discount);
    }
}
