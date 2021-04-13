package ru.job4j.solid.lsp.parking;

import java.util.List;

public class Parking implements VehicleStorage {

    private int[] carAmountSpace;
    private int[] truckAmountSpace;
    private int carFreeSpace;
    private int truckFreeSpace;

    public Parking(int carFreeSpace, int truckFreeSpace) {
        this.carAmountSpace = new int[carFreeSpace];
        this.truckAmountSpace = new int[truckFreeSpace];
        this.carFreeSpace = carFreeSpace;
        this.truckFreeSpace = truckFreeSpace;
    }

    public int[] getCarAmountSpace() {
        return carAmountSpace;
    }

    public int[] getTruckAmountSpace() {
        return truckAmountSpace;
    }

    public int getCarFreeSpace() {
        return carFreeSpace;
    }

    public void setCarFreeSpace(int carFreeSpace) {
        this.carFreeSpace = carFreeSpace;
    }

    public int getTruckFreeSpace() {
        return truckFreeSpace;
    }

    public void setTruckFreeSpace(int truckFreeSpace) {
        this.truckFreeSpace = truckFreeSpace;
    }

    @Override
    public Vehicle getCar(int carCoordinate) {
        return null;
    }

    @Override
    public Vehicle getTruck(int truckCoordinate) {
        return null;
    }

    @Override
    public List<Vehicle> findAll() {
        return null;
    }

    @Override
    public boolean park(Vehicle vehicle) {
        return false;
    }

    @Override
    public boolean leaveParking(Vehicle vehicle) {
        return false;
    }
}
