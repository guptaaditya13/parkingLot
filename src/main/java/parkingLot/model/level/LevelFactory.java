package parkingLot.model.level;


import parkingLot.model.slot.SlotFactory;
import parkingLot.model.level.selectionStrategy.NearestFirst;
import parkingLot.model.level.selectionStrategy.SelectionStrategy;

public class LevelFactory {
    public AbstractLevel createLevel(String type, int id, int parkingSlots, String slotSelectionStrategy, String slotType, SlotFactory slotFactory){
        if ("simple".equals(type)){
            return new SimpleLevel(id, parkingSlots, getSelectionStrategy(slotSelectionStrategy), slotFactory, slotType);
        }
        return null;
    }

    private SelectionStrategy getSelectionStrategy(String type){
        if ("nearestFirst".equals(type)){
            return new NearestFirst();
        }
        return null;
    }
}
