package parkingLot.services;

import parkingLot.model.parkingLevel.ParkingLevelFactory;
import parkingLot.model.parkingLot.AbstractParkingLot;
import parkingLot.model.parkingLot.ParkingLotFactory;
import parkingLot.model.parkingSlot.ParkingSlotFactory;
import parkingLot.model.parkingTicket.ParkingTicket;
import parkingLot.model.vehicle.Vehicle;
import parkingLot.settings.Constants;

import java.util.List;
import java.util.stream.Collectors;

public class ParkingLotService {
    AbstractParkingLot parkingLot;
    private final ParkingSlotFactory parkingSlotFactory;
    private final ParkingLevelFactory parkingLevelFactory;
    private final ParkingLotFactory parkingLotFactory;

    public ParkingLotService(ParkingSlotFactory parkingSlotFactory, ParkingLevelFactory parkingLevelFactory, ParkingLotFactory parkingLotFactory) {
        this.parkingSlotFactory = parkingSlotFactory;
        this.parkingLevelFactory = parkingLevelFactory;
        this.parkingLotFactory = parkingLotFactory;
    }

    public ParkingLotService() {
        parkingSlotFactory = new ParkingSlotFactory();
        parkingLevelFactory = new ParkingLevelFactory();
        parkingLotFactory = new ParkingLotFactory();
    }

    public void create(String parkingLotType, int levels, int slots, String levelType, String levelSelectionStrategy, String slotType, String slotSelectionStrategy) throws Exception {
        if(parkingLot == null) {
            parkingLot = parkingLotFactory.createParkingLot(parkingLotType, levels, slots, levelType, levelSelectionStrategy, slotType, slotSelectionStrategy, parkingLevelFactory, parkingSlotFactory);
        } else {
            throw new Exception("Parking lot already exists");
        }
    }

    public void create(int levels, int slots) throws Exception {
        create(Constants.DEFAULT_LOT_TYPE, levels, slots, Constants.DEFAULT_LEVEL_TYPE, Constants.DEFAULT_LEVEL_SELECTION_STRATEGY, Constants.DEFAULT_SLOT_TYPE, Constants.DEFAULT_SLOT_SELECTION_STRATEGY);
    }

    public ParkingTicket park(Vehicle vehicle){
        return parkingLot.park(vehicle);
    }

    public Vehicle release(String ticket){
        return parkingLot.free(ParkingTicket.condensedSlotToList(ticket));
    }

    public List<String> getAvailableSlots(){
        return parkingLot.getAllVacant().stream().map(ParkingTicket::getCondensedSlot).collect(Collectors.toList());
    }

    public List<String> getOccupiedSlots(){
        return parkingLot.getAllOccupied().stream().map(ParkingTicket::toString).collect(Collectors.toList());
    }
}
