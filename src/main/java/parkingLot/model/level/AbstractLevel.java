package parkingLot.model.level;

import parkingLot.Exception.ParkingSpaceEmptyException;
import parkingLot.Exception.ParkingSpaceFullException;
import parkingLot.Exception.UnknownParkingSpaceException;
import parkingLot.model.ticket.Ticket;
import parkingLot.model.vehicle.Vehicle;

import java.util.List;

public interface AbstractLevel {
    Ticket park(Vehicle vehicle) throws ParkingSpaceFullException;
    Vehicle free(List<Integer> slots) throws UnknownParkingSpaceException, ParkingSpaceEmptyException;
    boolean isFull();
    int getId();
    List<Ticket> getAllVacantSlots();
    List<Ticket> getAllOccupied();
}
