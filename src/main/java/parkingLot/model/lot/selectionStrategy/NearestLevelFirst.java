package parkingLot.model.lot.selectionStrategy;

import parkingLot.Exception.ParkingSpaceFullException;
import parkingLot.model.level.AbstractLevel;
import parkingLot.model.ticket.Ticket;
import parkingLot.model.vehicle.Vehicle;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class NearestLevelFirst implements LevelSelectionStrategy{
    private final PriorityQueue<AbstractLevel> parkingLevels;

    public NearestLevelFirst() {
        parkingLevels = new PriorityQueue<>(Comparator.comparingInt(AbstractLevel::getId));
    }

    @Override
    public AbstractLevel nextLevel(Vehicle vehicle) throws ParkingSpaceFullException {
        AbstractLevel parkingLevel = parkingLevels.poll();
        if (parkingLevel == null){
            throw new ParkingSpaceFullException();
        }
        return parkingLevel;
    }

    @Override
    public void addLevel(AbstractLevel parkingLevel) {
        parkingLevels.add(parkingLevel);
    }

    @Override
    public boolean isFull() {
        return parkingLevels.isEmpty();
    }

    @Override
    public List<Ticket> getAllVacantSlots() {
        return parkingLevels.stream().flatMap(x->x.getAllVacantSlots().stream()).collect(Collectors.toList());
    }
}
