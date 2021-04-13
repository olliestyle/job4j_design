package ru.job4j.solid.lsp.parking;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingTest {

    Parking parking = new Parking(5, 5);
    Car car1 = new Car("Ivan");
    Car car2 = new Car("Oleg");
    Car car3 = new Car("Egor");
    Truck truck1 = new Truck(2, "Semen");
    Truck truck2 = new Truck(2, "Pavel");
    Truck truck3 = new Truck(3, "Dima");
    Truck truck4 = new Truck(4, "Alex");

    @Test(expected = IllegalArgumentException.class)
    public void whenBadTruckSize() {
        Truck truck = new Truck(-1, "Kirill");
    }

    @Test
    public void whenParkFit() {
        assertTrue(parking.park(car1));
        assertTrue(parking.park(car2));
        assertTrue(parking.park(car3));
        assertTrue(parking.park(truck1));
        assertTrue(parking.park(truck2));
        assertTrue(parking.park(truck3));
        assertArrayEquals(new int[] {1, 1, 1, 2, 2}, parking.getCarAmountSpace());
        assertArrayEquals(new int[] {2, 2, 3, 3, 3}, parking.getTruckAmountSpace());
    }

    @Test
    public void whenParkDontFit() {
        assertTrue(parking.park(car1));
        assertTrue(parking.park(car2));
        assertTrue(parking.park(car3));
        assertTrue(parking.park(truck1));
        assertTrue(parking.park(truck2));
        assertTrue(parking.park(truck3));
        assertFalse(parking.park(truck4));
    }

    @Test
    public void whenParkingFullFree() {
        assertTrue(parking.park(car1));
        assertTrue(parking.park(car2));
        assertTrue(parking.park(car3));
        assertTrue(parking.park(truck1));
        assertTrue(parking.park(truck2));
        assertTrue(parking.park(truck3));
        assertTrue(parking.leaveParking(car1));
        assertTrue(parking.leaveParking(car2));
        assertTrue(parking.leaveParking(car3));
        assertTrue(parking.leaveParking(truck1));
        assertTrue(parking.leaveParking(truck2));
        assertTrue(parking.leaveParking(truck3));
        assertArrayEquals(new int[] {0, 0, 0, 0, 0}, parking.getCarAmountSpace());
        assertArrayEquals(new int[] {0, 0, 0, 0, 0}, parking.getTruckAmountSpace());
    }

    @Test
    public void whenNeedToRecogniseOwner() {
        assertTrue(parking.park(truck3));
        assertEquals("Dima", parking.getTruck(1).getOwner());
    }
}