package parkingLot.model.parkingLevel.selectionStrategy;

import parkingLot.Exceptions.ParkingSpaceFullException;
import parkingLot.model.parkingSlot.AbstractParkingSlot;
import parkingLot.model.parkingTicket.ParkingTicket;
import parkingLot.model.vehicle.Vehicle;

import java.util.List;

public interface SelectionStrategy {
    AbstractParkingSlot nextSlot(Vehicle vehicle) throws ParkingSpaceFullException;
    void addSlot(AbstractParkingSlot parkingSlot);
    boolean isFull();
    List<ParkingTicket> getAllVacantSlots();
}
