package parkingLot.model.lot.selectionStrategy;

import parkingLot.Exception.ParkingSpaceFullException;
import parkingLot.model.level.AbstractLevel;
import parkingLot.model.ticket.Ticket;
import parkingLot.model.vehicle.Vehicle;

import java.util.List;

public interface LevelSelectionStrategy {
    AbstractLevel nextLevel(Vehicle vehicle) throws ParkingSpaceFullException;
    void addLevel(AbstractLevel parkingLevel);
    boolean isFull();
    List<Ticket> getAllVacantSlots();
}
