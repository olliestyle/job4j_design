package ru.job4j.solid.lsp.productstorage.store;

import ru.job4j.solid.lsp.productstorage.model.Food;

import java.util.List;
import java.util.function.Predicate;

public interface Storable {
    List<Food> findAll();
    void put(Food food, long percentage);
}
