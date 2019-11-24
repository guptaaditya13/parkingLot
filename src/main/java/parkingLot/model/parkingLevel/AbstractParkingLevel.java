package parkingLot.model.parkingLevel;

import parkingLot.Exceptions.ParkingSpaceEmptyException;
import parkingLot.Exceptions.ParkingSpaceFullException;
import parkingLot.Exceptions.UnknownParkingSpaceException;
import parkingLot.model.parkingTicket.ParkingTicket;
import parkingLot.model.vehicle.Vehicle;

import java.util.List;

public interface AbstractParkingLevel {
    ParkingTicket park(Vehicle vehicle) throws ParkingSpaceFullException;
    Vehicle free(List<Integer> slots) throws UnknownParkingSpaceException, ParkingSpaceEmptyException;
    boolean isFull();
    int getId();
    List<ParkingTicket> getAllVacantSlots();
    List<ParkingTicket> getAllOccupied();
}
