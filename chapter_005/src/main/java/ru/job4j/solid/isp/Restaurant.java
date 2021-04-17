package ru.job4j.solid.isp;

public interface Restaurant {
    String frenchMenu();
    String japaneseMenu();
    String georgianMenu();
}

/**
 * Разделяем на
 * public interface FrenchRestaurant {
 *     String frenchMenu();
 * }
 * public interface JapaneseRestaurant {
 *     String japaneseMenu();
 * }
 *
 * public interfaceGeorgianRestaurant {
 *     String georgianMenu();
 * }
 */
