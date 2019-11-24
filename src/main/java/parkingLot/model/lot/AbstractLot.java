package parkingLot.model.lot;

import parkingLot.Exception.ParkingSpaceEmptyException;
import parkingLot.Exception.ParkingSpaceFullException;
import parkingLot.Exception.UnknownParkingSpaceException;
import parkingLot.model.ticket.Ticket;
import parkingLot.model.vehicle.Vehicle;

import java.util.List;

public interface AbstractLot {
    Ticket park(Vehicle vehicle) throws ParkingSpaceFullException;
    Vehicle free(List<Integer> slots) throws ParkingSpaceEmptyException, UnknownParkingSpaceException;
    List<Ticket> getAllVacant();
    List<Ticket> getAllOccupied();
}
