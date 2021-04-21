package ru.job4j.solid.lsp.productstorage.store;

import ru.job4j.solid.lsp.productstorage.model.Food;

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
    public boolean put(Food food, long percentage) {
        boolean rsl = false;
        if (percentage >= 25 && percentage <= 75) {
            foodList.add(food);
            rsl = true;
        } else if (percentage > 75 && percentage <= 100) {
            foodList.add(food);
            food.setDiscount(25);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public void clear() {
        this.foodList.clear();
    }

}
