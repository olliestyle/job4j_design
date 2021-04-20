package ru.job4j.solid.lsp.productstorage.store;

import ru.job4j.solid.lsp.productstorage.model.Food;

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
    public boolean put(Food food, long percentage) {
        return percentage < 25 && foodList.add(food);
    }

    @Override
    public boolean removeAll(List<Food> foodList) {
        return this.foodList.removeAll(foodList);
    }
}
