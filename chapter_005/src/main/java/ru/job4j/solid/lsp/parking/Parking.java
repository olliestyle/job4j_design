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

    /**
     * В зависимости от размера машины, выбираем парковку и заполняем пустые места.
     * @param vehicle
     * @return false при отсутствии свободных мест на парковке, в противном случае true.
     */
    @Override
    public boolean park(Vehicle vehicle) {
        boolean rsl = false;
        int size = vehicle.size();
        int fromFreeCar = indexFreeSpaceExist(carAmountSpace, size);
        if (size == 1) {
            if (fromFreeCar != -1) {
                for (int i = fromFreeCar; i < size + fromFreeCar; i++) {
                    carAmountSpace[i] = size;
                }
                rsl = true;
            }
        } else if (size >= 2) {
            int fromFreeTruck = indexFreeSpaceExist(truckAmountSpace, size);
            if (fromFreeTruck != -1) {
                for (int i = fromFreeTruck; i < size + fromFreeTruck; i++) {
                    truckAmountSpace[i] = size;
                }
                rsl = true;
            } else if (fromFreeCar != -1) {
                for (int i = fromFreeCar; i < size + fromFreeCar; i++) {
                    carAmountSpace[i] = size;
                }
                rsl = true;
            }
        }
        return rsl;
    }

    /**
     * Вычисляем индекс на парковке, с которого можно начать парковать ТС так, чтобы ТС влезло.
     * @param amount
     * @param size
     * @return freeIndex, если есть место на парковке, в противном случае -1
     */
    private int indexFreeSpaceExist(int[] amount, int size) {
        int freeIndex = -1;
        int needSpace = size;
        for (int i = 0; i <= amount.length - size; i++) {
            for (int j = i; j < size + i; j++) {
                if (amount[i] == 0) {
                    needSpace--;
                } else {
                    needSpace = size;
                }
            }
            if (needSpace == 0) {
                freeIndex = i;
                break;
            }
        }
        return freeIndex;
    }

    @Override
    public boolean leaveParking(Vehicle vehicle) {
        int size = vehicle.size();
        return false;
    }
}
