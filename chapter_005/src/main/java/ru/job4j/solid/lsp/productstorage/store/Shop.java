package ru.job4j.solid.lsp.productstorage.store;

import ru.job4j.solid.lsp.productstorage.model.Food;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Shop implements Storable {

    private List<Food> foodList = new ArrayList<>();

    public Shop() {

    }

    @Override
    public List<Food> findAll() {
        return this.foodList;
    }

    @Override
    public void put(Food food, long percentage) {
        if (percentage >= 25 && percentage <= 75) {
            foodList.add(food);
        } else if (percentage > 75 && percentage <= 100) {
            foodList.add(food);
            food.setDiscount(25);
        }
    }
}
