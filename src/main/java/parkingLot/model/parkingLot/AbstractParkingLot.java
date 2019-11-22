package parkingLot.model.parkingLot;

import parkingLot.model.parkingTicket.ParkingTicket;
import parkingLot.model.vehicle.Vehicle;

import java.util.List;

public interface AbstractParkingLot {
    ParkingTicket park(Vehicle vehicle);
    Vehicle free(List<Integer> slots);
    List<ParkingTicket> getAllVacant();
    List<ParkingTicket> getAllOccupied();
}
