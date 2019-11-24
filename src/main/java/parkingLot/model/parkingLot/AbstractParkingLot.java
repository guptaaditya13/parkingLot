package parkingLot.model.parkingLot;

import parkingLot.Exceptions.ParkingSpaceEmptyException;
import parkingLot.Exceptions.ParkingSpaceFullException;
import parkingLot.Exceptions.UnknownParkingSpaceException;
import parkingLot.model.parkingTicket.ParkingTicket;
import parkingLot.model.vehicle.Vehicle;

import java.util.List;

public interface AbstractParkingLot {
    ParkingTicket park(Vehicle vehicle) throws ParkingSpaceFullException;
    Vehicle free(List<Integer> slots) throws ParkingSpaceEmptyException, UnknownParkingSpaceException;
    List<ParkingTicket> getAllVacant();
    List<ParkingTicket> getAllOccupied();
}
