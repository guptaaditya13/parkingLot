package parkingLot.service;

import parkingLot.Exception.ParkingSpaceEmptyException;
import parkingLot.Exception.ParkingSpaceFullException;
import parkingLot.Exception.UnknownParkingSpaceException;
import parkingLot.model.level.LevelFactory;
import parkingLot.model.lot.AbstractLot;
import parkingLot.model.lot.LotFactory;
import parkingLot.model.slot.SlotFactory;
import parkingLot.model.ticket.Ticket;
import parkingLot.model.vehicle.Vehicle;
import parkingLot.setting.Constants;

import java.util.List;
import java.util.stream.Collectors;

public class LotService {
    AbstractLot parkingLot;
    private final SlotFactory slotFactory;
    private final LevelFactory levelFactory;
    private final LotFactory lotFactory;

    public LotService(SlotFactory slotFactory, LevelFactory levelFactory, LotFactory lotFactory) {
        this.slotFactory = slotFactory;
        this.levelFactory = levelFactory;
        this.lotFactory = lotFactory;
    }

    public LotService() {
        slotFactory = new SlotFactory();
        levelFactory = new LevelFactory();
        lotFactory = new LotFactory();
    }

    public void create(String parkingLotType, int levels, int slots, String levelType, String levelSelectionStrategy, String slotType, String slotSelectionStrategy) throws Exception {
        if(parkingLot == null) {
            parkingLot = lotFactory.createParkingLot(parkingLotType, levels, slots, levelType, levelSelectionStrategy, slotType, slotSelectionStrategy, levelFactory, slotFactory);
        } else {
            throw new Exception("Parking lot already exists");
        }
    }

    public void create(int levels, int slots) throws Exception {
        create(Constants.DEFAULT_LOT_TYPE, levels, slots, Constants.DEFAULT_LEVEL_TYPE, Constants.DEFAULT_LEVEL_SELECTION_STRATEGY, Constants.DEFAULT_SLOT_TYPE, Constants.DEFAULT_SLOT_SELECTION_STRATEGY);
    }

    public Ticket park(Vehicle vehicle) throws ParkingSpaceFullException{
        return parkingLot.park(vehicle);
    }

    public Vehicle release(String ticket) throws ParkingSpaceEmptyException, UnknownParkingSpaceException{
        return parkingLot.free(Ticket.condensedSlotToList(ticket));
    }

    public List<String> getAvailableSlots(){
        return parkingLot.getAllVacant().stream().map(Ticket::getCondensedSlot).collect(Collectors.toList());
    }

    public List<String> getOccupiedSlots(){
        return parkingLot.getAllOccupied().stream().map(Ticket::toString).collect(Collectors.toList());
    }
}
