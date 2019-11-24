package parkingLot.model.lot;

import org.apache.log4j.Logger;
import parkingLot.Exception.ParkingSpaceEmptyException;
import parkingLot.Exception.ParkingSpaceFullException;
import parkingLot.Exception.UnknownParkingSpaceException;
import parkingLot.model.level.LevelFactory;
import parkingLot.model.level.AbstractLevel;
import parkingLot.model.lot.selectionStrategy.LevelSelectionStrategy;
import parkingLot.model.slot.SlotFactory;
import parkingLot.model.ticket.Ticket;
import parkingLot.model.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

class SimpleLot implements AbstractLot {
    private final LevelSelectionStrategy levelSelectionStrategy;
    private final HashSet<AbstractLevel> occupiedLevels;
    private final List<AbstractLevel> allLevels;
    private final int levelCount;
    private static final Logger log = Logger.getLogger(SimpleLot.class);


    SimpleLot(int levels, int slots, String levelType, LevelSelectionStrategy levelSelectionStrategy, String slotType,
              String slotSelectionStrategy, LevelFactory levelFactory, SlotFactory slotFactory) {
        this.levelSelectionStrategy = levelSelectionStrategy;
        allLevels = new ArrayList<>(levels);
        levelCount = levels;
        for (int i = 0; i < levels; i++) {
            AbstractLevel level = levelFactory.createLevel(levelType, i + 1, slots, slotSelectionStrategy, slotType, slotFactory);
            this.levelSelectionStrategy.addLevel(level);
            allLevels.add(level);
        }
        occupiedLevels = new HashSet<>();
    }

    @Override
    public Ticket park(Vehicle vehicle) throws ParkingSpaceFullException {
        log.debug("Parking :: " + vehicle);
        AbstractLevel abstractLevel = null;
        Ticket ticket;
        try {
            abstractLevel = levelSelectionStrategy.nextLevel(vehicle);
            ticket = abstractLevel.park(vehicle);
            if (abstractLevel.isFull()) {
                occupiedLevels.add(abstractLevel);
            } else {
                levelSelectionStrategy.addLevel(abstractLevel);
            }
            log.debug("Issued ticket :: " + ticket);
            return ticket;
        } catch (ParkingSpaceFullException e) {
            if (abstractLevel != null) {
                occupiedLevels.add(abstractLevel);
                return park(vehicle);
            }
            throw e;
        }
    }

    @Override
    public Vehicle free(List<Integer> slots) throws ParkingSpaceEmptyException, UnknownParkingSpaceException {
        log.debug("Removing :: " + slots);
        int levelId = slots.get(0) - 1;
        if (levelId >= 0 && levelId < levelCount) {
            AbstractLevel abstractLevel = allLevels.get(levelId);
            Vehicle vehicle = abstractLevel.free(slots.subList(1, slots.size()));
            if (!abstractLevel.isFull() && occupiedLevels.contains(abstractLevel)) {
                occupiedLevels.remove(abstractLevel);
                levelSelectionStrategy.addLevel(abstractLevel);
            }
            log.debug("returning vehicle :: " + vehicle);
            return vehicle;
        } else {
            throw new UnknownParkingSpaceException();
        }
    }

    @Override
    public List<Ticket> getAllVacant() {
        return levelSelectionStrategy.getAllVacantSlots();
    }

    @Override
    public List<Ticket> getAllOccupied() {
        return allLevels.stream().flatMap(x -> x.getAllOccupied().stream()).collect(Collectors.toList());
    }

}
