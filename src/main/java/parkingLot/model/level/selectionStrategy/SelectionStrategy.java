package parkingLot.model.level.selectionStrategy;

import parkingLot.Exception.ParkingSpaceFullException;
import parkingLot.model.slot.AbstractSlot;
import parkingLot.model.ticket.Ticket;
import parkingLot.model.vehicle.Vehicle;

import java.util.List;

public interface SelectionStrategy {
    AbstractSlot nextSlot(Vehicle vehicle) throws ParkingSpaceFullException;
    void addSlot(AbstractSlot parkingSlot);
    boolean isFull();
    List<Ticket> getAllVacantSlots();
}
