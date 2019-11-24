package parkingLot.model.parkingLot.selectionStrategy;

import parkingLot.Exceptions.ParkingSpaceFullException;
import parkingLot.model.parkingLevel.AbstractParkingLevel;
import parkingLot.model.parkingTicket.ParkingTicket;
import parkingLot.model.vehicle.Vehicle;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class NearestLevelFirstStrategy implements LevelSelectionStrategy{
    private final PriorityQueue<AbstractParkingLevel> parkingLevels;

    public NearestLevelFirstStrategy() {
        parkingLevels = new PriorityQueue<>(Comparator.comparingInt(AbstractParkingLevel::getId));
    }

    @Override
    public AbstractParkingLevel nextLevel(Vehicle vehicle) throws ParkingSpaceFullException {
        AbstractParkingLevel parkingLevel = parkingLevels.poll();
        if (parkingLevel == null){
            throw new ParkingSpaceFullException();
        }
        return parkingLevel;
    }

    @Override
    public void addLevel(AbstractParkingLevel parkingLevel) {
        parkingLevels.add(parkingLevel);
    }

    @Override
    public boolean isFull() {
        return parkingLevels.isEmpty();
    }

    @Override
    public List<ParkingTicket> getAllVacantSlots() {
        return parkingLevels.stream().flatMap(x->x.getAllVacantSlots().stream()).collect(Collectors.toList());
    }
}
