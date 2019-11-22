package parkingLot.model.parkingLot;

import org.apache.log4j.Logger;
import parkingLot.model.parkingLevel.ParkingLevelFactory;
import parkingLot.model.parkingLevel.AbstractParkingLevel;
import parkingLot.model.parkingLot.selectionStrategy.LevelSelectionStrategy;
import parkingLot.model.parkingLot.selectionStrategy.NearestLevelFirstStrategy;
import parkingLot.model.parkingSlot.ParkingSlotFactory;
import parkingLot.model.parkingTicket.ParkingTicket;
import parkingLot.model.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

class SimpleParkingLot implements AbstractParkingLot {
    private final LevelSelectionStrategy levelSelectionStrategy;
    private final HashSet<AbstractParkingLevel> occupiedLevels;
    private final List<AbstractParkingLevel> allLevels;
    private static final Logger log = Logger.getLogger(SimpleParkingLot.class);


    SimpleParkingLot(int levels, int slots, String levelType, LevelSelectionStrategy levelSelectionStrategy, String slotType,
                             String slotSelectionStrategy, ParkingLevelFactory parkingLevelFactory, ParkingSlotFactory parkingSlotFactory) {
        this.levelSelectionStrategy = levelSelectionStrategy;
        allLevels = new ArrayList<>(levels);
        for (int i = 0; i < levels; i++) {
            AbstractParkingLevel level = parkingLevelFactory.createLevel(levelType, i + 1, slots, slotSelectionStrategy, slotType, parkingSlotFactory);
            this.levelSelectionStrategy.addLevel(level);
            allLevels.add(level);
        }
        occupiedLevels = new HashSet<>();
    }

    @Override
    public ParkingTicket park(Vehicle vehicle) {
        log.debug("Parking :: " + vehicle);
        AbstractParkingLevel abstractParkingLevel = levelSelectionStrategy.nextLevel(vehicle);
        ParkingTicket parkingTicket = abstractParkingLevel.park(vehicle);
        if (abstractParkingLevel.isFull()) {
            occupiedLevels.add(abstractParkingLevel);
        } else {
            levelSelectionStrategy.addLevel(abstractParkingLevel);
        }
        log.debug("Issued ticket :: " + parkingTicket);
        return parkingTicket;
    }

    @Override
    public Vehicle free(List<Integer> slots) {
        log.debug("Removing :: " + slots);
        AbstractParkingLevel abstractParkingLevel = allLevels.get(slots.get(0)-1);
        Vehicle vehicle = abstractParkingLevel.free(slots.subList(1, slots.size()));
        if (!abstractParkingLevel.isFull() && occupiedLevels.contains(abstractParkingLevel)){
            occupiedLevels.remove(abstractParkingLevel);
            levelSelectionStrategy.addLevel(abstractParkingLevel);
        }
        log.debug("returning vehicle :: " + vehicle);
        return vehicle;
    }

    @Override
    public List<ParkingTicket> getAllVacant() {
        return levelSelectionStrategy.getAllVacantSlots();
    }

    @Override
    public List<ParkingTicket> getAllOccupied() {
        return allLevels.stream().flatMap(x->x.getAllOccupied().stream()).collect(Collectors.toList());
    }

}
