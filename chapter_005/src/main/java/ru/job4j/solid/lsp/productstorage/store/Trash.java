package ru.job4j.solid.lsp.productstorage.store;

import ru.job4j.solid.lsp.productstorage.model.Food;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Trash implements Storable {

    List<Food> foodList = new ArrayList<>();

    public Trash() {

    }

    @Override
    public List<Food> findAll() {
        return this.foodList;
    }

    @Override
    public void put(Food food, long percentage) {
        if (percentage > 100) {
            foodList.add(food);
        }
    }
}
