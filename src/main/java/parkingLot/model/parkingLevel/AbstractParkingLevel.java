package parkingLot.model.parkingLevel;

import parkingLot.model.parkingTicket.ParkingTicket;
import parkingLot.model.vehicle.Vehicle;

import java.util.List;

public interface AbstractParkingLevel {
    ParkingTicket park(Vehicle vehicle);
    Vehicle free(List<Integer> slots);
    boolean isFull();
    int getId();
    List<ParkingTicket> getAllVacantSlots();
    List<ParkingTicket> getAllOccupied();
}
