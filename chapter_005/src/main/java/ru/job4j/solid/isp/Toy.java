package ru.job4j.solid.isp;

public interface Toy {
    void sing();
    void dance();
    void move();
}

/**
 * Разделяем на
 * public interface SingAbleToy {
 *     void sing();
 * }
 *
 * public interface DanceAbleToy {
 *     void dance();
 * }
 *
 * public interface MoveAbleToy {
 *     void move();
 * }
 */
