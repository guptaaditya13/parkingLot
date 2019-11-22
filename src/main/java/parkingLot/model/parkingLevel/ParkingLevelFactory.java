package parkingLot.model.parkingLevel;


import parkingLot.model.parkingSlot.ParkingSlotFactory;
import parkingLot.model.parkingLevel.selectionStrategy.NearestFirstStrategy;
import parkingLot.model.parkingLevel.selectionStrategy.SelectionStrategy;

public class ParkingLevelFactory {
    public AbstractParkingLevel createLevel(String type, int id, int parkingSlots, String slotSelectionStrategy, String slotType, ParkingSlotFactory parkingSlotFactory){
        if ("simple".equals(type)){
            return new ParkingLevel(id, parkingSlots, getSelectionStrategy(slotSelectionStrategy), parkingSlotFactory, slotType);
        }
        return null;
    }

    private SelectionStrategy getSelectionStrategy(String type){
        if ("nearestFirst".equals(type)){
            return new NearestFirstStrategy();
        }
        return null;
    }
}
