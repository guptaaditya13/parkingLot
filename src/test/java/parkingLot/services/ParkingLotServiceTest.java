package parkingLot.services;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import parkingLot.model.parkingTicket.ParkingTicket;
import parkingLot.model.vehicle.Vehicle;

class ParkingLotServiceTest {
    private static ParkingLotService parkingLotService;
    private static int levels = 3;
    private static int slots = 2;
    ParkingTicket ticket;
    private static final Logger log = Logger.getLogger(ParkingLotServiceTest.class);
    @BeforeAll
    static void init() throws Exception {
        parkingLotService = new ParkingLotService();
        parkingLotService.create(levels,slots);
    }

    @Test
    void create() throws Exception {

    }

    @Test
    void park() {
        Vehicle vehicle =  new Vehicle("bla", "blue");
        ticket = parkingLotService.park(vehicle);
        Assertions.assertEquals("1-1", ticket.getCondensedSlot());
        Assertions.assertEquals(vehicle, ticket.getVehicle());


        vehicle =  new Vehicle("bla", "red");
        ticket = parkingLotService.park(vehicle);
        Assertions.assertEquals("1-2", ticket.getCondensedSlot());
        Assertions.assertEquals(vehicle, ticket.getVehicle());
        log.info("Available Slots :: \n" + String.join("\n",parkingLotService.getAvailableSlots()));
        Vehicle vehicle1 = vehicle;

        vehicle =  new Vehicle("bla", "black");
        ticket = parkingLotService.park(vehicle);
        Assertions.assertEquals("2-1", ticket.getCondensedSlot());
        Assertions.assertEquals(vehicle, ticket.getVehicle());
        log.info("Available Slots :: \n" + String.join("\n",parkingLotService.getAvailableSlots()));
        log.info("Occupied Slots :: \n" + String.join("\n", parkingLotService.getOccupiedSlots()));

        Assertions.assertEquals(parkingLotService.release("1-2"), vehicle1);
        log.info("Available Slots :: \n" + String.join("\n",parkingLotService.getAvailableSlots()));
        log.info("Occupied Slots :: \n" + String.join("\n", parkingLotService.getOccupiedSlots()));


    }

    @Test
    void release() {
    }

}