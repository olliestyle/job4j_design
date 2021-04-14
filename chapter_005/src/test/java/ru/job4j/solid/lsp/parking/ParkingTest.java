package ru.job4j.solid.lsp.parking;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingTest {

    Parking parking = new Parking(5, 5);

    @Test(expected = IllegalArgumentException.class)
    public void whenBadTruckSize() {
        Truck truck = new Truck(-1);
    }

    @Test
    public void whenParkFit() {
        assertTrue(parking.park(new Car()));
        assertTrue(parking.park(new Car()));
        assertTrue(parking.park(new Car()));
        assertTrue(parking.park(new Truck(2)));
        assertTrue(parking.park(new Truck(3)));
        assertTrue(parking.park(new Truck(2)));
        assertArrayEquals(new int[] {1, 1, 1, 2, 2}, parking.getCarAmountSpace());
        assertArrayEquals(new int[] {2, 2, 3, 3, 3}, parking.getTruckAmountSpace());
    }

    @Test
    public void whenParkDontFit() {
        assertTrue(parking.park(new Car()));
        assertTrue(parking.park(new Car()));
        assertTrue(parking.park(new Car()));
        assertTrue(parking.park(new Truck(2)));
        assertTrue(parking.park(new Truck(3)));
        assertTrue(parking.park(new Truck(2)));
        assertFalse(parking.park(new Truck(2)));
    }

    @Test
    public void whenParkingFullFree() {
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Truck truck1 = new Truck(2);
        Truck truck2 = new Truck(2);
        Truck truck3 = new Truck(3);
        assertTrue(parking.park(car1));
        assertTrue(parking.park(car2));
        assertTrue(parking.park(car3));
        assertTrue(parking.park(truck1));
        assertTrue(parking.park(truck3));
        assertTrue(parking.park(truck2));
        assertTrue(parking.leaveParking(car1));
        assertTrue(parking.leaveParking(car2));
        assertTrue(parking.leaveParking(car3));
        assertTrue(parking.leaveParking(truck1));
        assertTrue(parking.leaveParking(truck2));
        assertTrue(parking.leaveParking(truck3));
        assertArrayEquals(new int[] {0, 0, 0, 0, 0}, parking.getCarAmountSpace());
        assertArrayEquals(new int[] {0, 0, 0, 0, 0}, parking.getTruckAmountSpace());
    }
}