package parkingLot.model.parkingLevel.selectionStrategy;

import parkingLot.Exceptions.ParkingSpaceFullException;
import parkingLot.model.parkingSlot.AbstractParkingSlot;
import parkingLot.model.parkingTicket.ParkingTicket;
import parkingLot.model.parkingTicket.UnassignedTicket;
import parkingLot.model.vehicle.Vehicle;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class NearestFirstStrategy implements SelectionStrategy {
    private final PriorityQueue<AbstractParkingSlot> slots;

    public NearestFirstStrategy() {
        slots = new PriorityQueue<>(Comparator.comparingInt(AbstractParkingSlot::getId));
    }

    @Override
    public AbstractParkingSlot nextSlot(Vehicle vehicle) throws ParkingSpaceFullException {
        AbstractParkingSlot parkingSlot = slots.poll();
        if (parkingSlot == null){
            throw new ParkingSpaceFullException();
        }
        return parkingSlot;
    }

    @Override
    public void addSlot(AbstractParkingSlot parkingSlot) {
        slots.add(parkingSlot);
    }

    @Override
    public boolean isFull() {
        return slots.isEmpty();
    }

    @Override
    public List<ParkingTicket> getAllVacantSlots(){
        return slots.stream().map(x->new UnassignedTicket(x.getId())).collect(Collectors.toList());
    }
}
