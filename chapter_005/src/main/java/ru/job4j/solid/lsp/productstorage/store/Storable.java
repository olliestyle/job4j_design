package ru.job4j.solid.lsp.productstorage.store;

import ru.job4j.solid.lsp.productstorage.model.Food;

import java.util.List;

public interface Storable {
    List<Food> findAll();
    boolean put(Food food, long percentage);
}
