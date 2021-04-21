package ru.job4j.solid.lsp.productstorage.store;

import ru.job4j.solid.lsp.productstorage.model.Food;

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
    public boolean put(Food food, long percentage) {
        return percentage > 100 && foodList.add(food);
    }

    @Override
    public void clear() {
        this.foodList.clear();
    }


}
