package parkingLot.model.parkingLot;

import parkingLot.model.parkingLevel.ParkingLevelFactory;
import parkingLot.model.parkingLot.selectionStrategy.LevelSelectionStrategy;
import parkingLot.model.parkingLot.selectionStrategy.NearestLevelFirstStrategy;
import parkingLot.model.parkingSlot.ParkingSlotFactory;

public class ParkingLotFactory {

    public AbstractParkingLot createParkingLot( String parkingLotType,
            int levels, int slots, String levelType, String levelSelectionStrategy, String slotType,
            String slotSelectionStrategy, ParkingLevelFactory parkingLevelFactory, ParkingSlotFactory parkingSlotFactory) {
        if ("simple".equals(parkingLotType)){
            return new SimpleParkingLot(levels, slots, levelType, getLevelSelectionStrategy(levelSelectionStrategy),
                    slotType, slotSelectionStrategy, parkingLevelFactory, parkingSlotFactory);
        }
        return null;
    }

    private LevelSelectionStrategy getLevelSelectionStrategy(String type){
        if ("nearestFirst".equals(type)){
            return new NearestLevelFirstStrategy();
        }
        return null;
    }
}
