package parkingLot.model.slot;

public class SlotFactory {
    public AbstractSlot createSlot(String type, int id){
        if("simple".equals(type)){
            return new SimpleSlot(id);
        }
        return null;
    }
}
