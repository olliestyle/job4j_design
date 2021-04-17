package ru.job4j.solid.isp;

public interface Couch {
    void assemble();
    void disAssemble();
    void storeStuff();
}

/**
 * Разделяем на
 * public interface AssembleAbleCouch {
 *     void assemble();
 *     void disAssemble();
 * }
 *
 * и
 *
 * public interface StoreAbleCouch {
 *     void storeStuff();
 * }
 */
