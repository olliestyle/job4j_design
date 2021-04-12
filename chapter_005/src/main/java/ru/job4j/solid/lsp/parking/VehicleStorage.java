package ru.job4j.solid.lsp.parking;

import java.util.List;

public interface VehicleStorage {
    Vehicle get();
    List<Vehicle> findAll();
}
