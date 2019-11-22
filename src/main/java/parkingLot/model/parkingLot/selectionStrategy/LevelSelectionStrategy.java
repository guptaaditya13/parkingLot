package parkingLot.model.parkingLot.selectionStrategy;

import parkingLot.model.parkingLevel.AbstractParkingLevel;
import parkingLot.model.parkingTicket.ParkingTicket;
import parkingLot.model.vehicle.Vehicle;

import java.util.List;

public interface LevelSelectionStrategy {
    AbstractParkingLevel nextLevel(Vehicle vehicle);
    void addLevel(AbstractParkingLevel parkingLevel);
    boolean isFull();
    List<ParkingTicket> getAllVacantSlots();
}
