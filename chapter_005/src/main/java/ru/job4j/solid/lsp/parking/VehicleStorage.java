package ru.job4j.solid.lsp.parking;

import java.util.List;

public interface VehicleStorage {
    Vehicle getCar(int carCoordinate);
    Vehicle getTruck(int truckCoordinate);
    List<Vehicle> findAll();
    boolean park(Vehicle vehicle);
    boolean leaveParking(Vehicle vehicle);
}
