package parkingLot.model.lot;

import parkingLot.model.level.LevelFactory;
import parkingLot.model.lot.selectionStrategy.LevelSelectionStrategy;
import parkingLot.model.lot.selectionStrategy.NearestLevelFirst;
import parkingLot.model.slot.SlotFactory;

public class LotFactory {

    public AbstractLot createParkingLot(String parkingLotType,
                                        int levels, int slots, String levelType, String levelSelectionStrategy, String slotType,
                                        String slotSelectionStrategy, LevelFactory levelFactory, SlotFactory slotFactory) {
        if ("simple".equals(parkingLotType)){
            return new SimpleLot(levels, slots, levelType, getLevelSelectionStrategy(levelSelectionStrategy),
                    slotType, slotSelectionStrategy, levelFactory, slotFactory);
        }
        return null;
    }

    private LevelSelectionStrategy getLevelSelectionStrategy(String type){
        if ("nearestFirst".equals(type)){
            return new NearestLevelFirst();
        }
        return null;
    }
}
