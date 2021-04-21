package ru.job4j.solid.lsp.productstorage.controll;

import ru.job4j.solid.lsp.productstorage.model.*;
import ru.job4j.solid.lsp.productstorage.store.Shop;
import ru.job4j.solid.lsp.productstorage.store.Storable;
import ru.job4j.solid.lsp.productstorage.store.Trash;
import ru.job4j.solid.lsp.productstorage.store.WareHouse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ControlQuality {

    Storable[] storables;

    public ControlQuality(Storable... storables) {
        this.storables = storables;
    }

    public void control(Food food) {
        long shelfLife = ChronoUnit.DAYS.between(food.getCreateDate().toLocalDate(), food.getExpiryDate().toLocalDate());
        long remainedDays = ChronoUnit.DAYS.between(LocalDate.now(), food.getExpiryDate().toLocalDate());
        long percentage = 100 - (remainedDays * 100 / shelfLife);
        for (Storable storable: storables) {
            if (storable.put(food, percentage)) {
                break;
            }
        }
    }

    public void resort() {
        List<Food> allFood = new ArrayList<>();
        for (Storable storable: storables) {
            List<Food> toResort = storable.findAll();
            allFood.addAll(toResort);
            storable.clear();
        }
        for (Food food: allFood) {
            control(food);
        }
    }

    public static void main(String[] args) {
        WareHouse wareHouse = new WareHouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(wareHouse, shop, trash);
        Milk milk = new Milk("moloko", LocalDateTime.now().minusDays(100), LocalDateTime.now().plusDays(10), 200, 0);
        Bread bread = new Bread("stolica", LocalDateTime.now().minusDays(5), LocalDateTime.now().plusDays(1), 300, 0);
        Egg egg = new Egg("lenta", LocalDateTime.now(), LocalDateTime.now().plusDays(1), 50, 0);
        Sausage sausage = new Sausage("kolbasa", LocalDateTime.now().minusDays(10), LocalDateTime.now().plusDays(100), 660, 0);
        Cheese cheese = new Cheese("lamber", LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(1), 250, 0);
        controlQuality.control(milk);
        controlQuality.control(bread);
        controlQuality.control(egg);
        controlQuality.control(sausage);
        controlQuality.control(cheese);
        controlQuality.resort();
    }
}
