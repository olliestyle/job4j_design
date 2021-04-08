package ru.job4j.solid.lsp.productstorage.store;

import ru.job4j.solid.lsp.productstorage.model.Food;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class WareHouse implements Storable {

    private List<Food> foodList = new ArrayList<>();

    public WareHouse() {

    }

    @Override
    public List<Food> findAll() {
        return this.foodList;
    }

    @Override
    public void put(Food food, long percentage) {
        if (percentage < 25) {
            foodList.add(food);
        }
    }
}
