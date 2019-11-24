package parkingLot.services;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import parkingLot.Exceptions.ParkingSpaceFullException;
import parkingLot.model.parkingTicket.ParkingTicket;
import parkingLot.model.vehicle.Car;
import parkingLot.model.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ParkingLotServiceTest {
    private static ParkingLotService parkingLotService;
    private static int levels = 2;
    private static int slots = 2;
    ParkingTicket ticket;
    private static final Logger log = Logger.getLogger(ParkingLotServiceTest.class);

    @BeforeAll
    static void init() throws Exception {
        parkingLotService = new ParkingLotService();
        parkingLotService.create(levels, slots);
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
            ParkingTicket parkingTicket = parkingLotService.park(v);
            parkingTickets.add(parkingTicket.getCondensedSlot());
            log.info("Parking Ticket " + parkingTicket);
        }
        try {
            Vehicle vehicle = new Car("KA-02-MH-1234", "black");
            log.info("Park : " + vehicle);
            parkingLotService.park(vehicle);
        } catch (ParkingSpaceFullException e){
            log.info("Sorry, Parking lot is full");
            Assertions.assertEquals(e.getClass(), ParkingSpaceFullException.class);
        }

        printOccupied();
        printVacant();

        log.info("Release " + parkingTickets.get(0));
        Vehicle vehicle = parkingLotService.release(parkingTickets.get(0));
        log.info("Released " + vehicle);
        Assertions.assertEquals(vehicles.get(0), vehicle);

        printOccupied();
        printVacant();



    }

    void printOccupied(){
        log.info("Get Occupied Slots");
        log.info("Occupied Slots :: \n" + String.join("\n", parkingLotService.getOccupiedSlots()));
    }
    void printVacant(){
        log.info("Get Available Slots");
        log.info("Available Slots :: \n" + String.join("\n", parkingLotService.getAvailableSlots()));
    }

}