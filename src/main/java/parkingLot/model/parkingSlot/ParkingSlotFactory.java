package parkingLot.model.parkingSlot;

public class ParkingSlotFactory {
    public AbstractParkingSlot createSlot(String type, int id){
        if("simple".equals(type)){
            return new SimpleParkingSlot(id);
        }
        return null;
    }
}
