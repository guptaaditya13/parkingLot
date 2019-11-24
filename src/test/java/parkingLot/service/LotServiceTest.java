package parkingLot.service;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import parkingLot.Exception.ParkingSpaceFullException;
import parkingLot.model.ticket.Ticket;
import parkingLot.model.vehicle.Car;
import parkingLot.model.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LotServiceTest {
    private static LotService lotService;
    private static int levels = 2;
    private static int slots = 2;
    private static final Logger log = Logger.getLogger(LotServiceTest.class);

    @BeforeAll
    static void init() throws Exception {
        lotService = new LotService();
        log.info(String.format("Create lot with Levels : %s, Slots/level : %s", levels, slots));
        lotService.create(levels, slots);
    }



    @Test
    void mainTestCase() throws Exception {
        List<Vehicle> vehicles = Arrays.asList(
                new Car("KA-01-HH-1234", "white"),
                new Car("KA-01-HH-1235", "blue"),
                new Car("KA-01-HH-1236", "pink"),
                new Car("KA-01-HH-1237", "green")
                );
        List<String> parkingTickets = new ArrayList<>();
        printOccupied();
        printVacant();
        for (int i = 0, vehiclesSize = vehicles.size(); i < vehiclesSize; i++) {
            Vehicle v = vehicles.get(i);
            log.info("Park " + v);
            Ticket ticket = lotService.park(v);
            parkingTickets.add(ticket.getCondensedSlot());
            log.info("Parking Ticket " + ticket);
        }
        try {
            Vehicle vehicle = new Car("KA-02-MH-1234", "black");
            log.info("Park : " + vehicle);
            lotService.park(vehicle);
        } catch (ParkingSpaceFullException e){
            log.info("Sorry, Parking lot is full");
            Assertions.assertEquals(e.getClass(), ParkingSpaceFullException.class);
        }

        printOccupied();
        printVacant();

        log.info("Release " + parkingTickets.get(0));
        Vehicle vehicle = lotService.release(parkingTickets.get(0));
        log.info("Released " + vehicle);
        Assertions.assertEquals(vehicles.get(0), vehicle);

        printOccupied();
        printVacant();



    }

    void printOccupied(){
        log.info("Get Occupied Slots");
        log.info("Occupied Slots :: \n" + String.join("\n", lotService.getOccupiedSlots()));
    }
    void printVacant(){
        log.info("Get Available Slots");
        log.info("Available Slots :: \n" + String.join("\n", lotService.getAvailableSlots()));
    }

}