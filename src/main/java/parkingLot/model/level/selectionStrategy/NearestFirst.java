package parkingLot.model.level.selectionStrategy;

import parkingLot.Exception.ParkingSpaceFullException;
import parkingLot.model.slot.AbstractSlot;
import parkingLot.model.ticket.Ticket;
import parkingLot.model.ticket.UnassignedTicket;
import parkingLot.model.vehicle.Vehicle;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class NearestFirst implements SelectionStrategy {
    private final PriorityQueue<AbstractSlot> slots;

    public NearestFirst() {
        slots = new PriorityQueue<>(Comparator.comparingInt(AbstractSlot::getId));
    }

    @Override
    public AbstractSlot nextSlot(Vehicle vehicle) throws ParkingSpaceFullException {
        AbstractSlot parkingSlot = slots.poll();
        if (parkingSlot == null){
            throw new ParkingSpaceFullException();
        }
        return parkingSlot;
    }

    @Override
    public void addSlot(AbstractSlot parkingSlot) {
        slots.add(parkingSlot);
    }

    @Override
    public boolean isFull() {
        return slots.isEmpty();
    }

    @Override
    public List<Ticket> getAllVacantSlots(){
        return slots.stream().map(x->new UnassignedTicket(x.getId())).collect(Collectors.toList());
    }
}
