package parkingLot.model.parkingLevel;

import org.apache.log4j.Logger;
import parkingLot.model.parkingSlot.AbstractParkingSlot;
import parkingLot.model.parkingSlot.ParkingSlotFactory;
import parkingLot.model.parkingLevel.selectionStrategy.SelectionStrategy;
import parkingLot.model.parkingTicket.ParkingTicket;
import parkingLot.model.parkingTicket.SimpleParkingTicket;
import parkingLot.model.vehicle.Vehicle;

import java.util.*;
import java.util.stream.Collectors;

class ParkingLevel implements AbstractParkingLevel {
    private final int id;
    private final Map<Integer, AbstractParkingSlot> occupiedParkingSlots;
    private SelectionStrategy selectionStrategy;
    private static final Logger log = Logger.getLogger(ParkingLevel.class);


    public ParkingLevel(int id, int parkingSlots, SelectionStrategy selectionStrategy, ParkingSlotFactory parkingSlotFactory, String slotType) {
        this.id = id;
        occupiedParkingSlots = new HashMap<>(parkingSlots);
        this.selectionStrategy = selectionStrategy;
        createSlots(parkingSlots, parkingSlotFactory, slotType);
    }

    @Override
    public boolean isFull() {
        return selectionStrategy.isFull();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public List<ParkingTicket> getAllVacantSlots() {
        List<ParkingTicket> vacantSlots = selectionStrategy.getAllVacantSlots();
        vacantSlots.forEach(vacantSlot -> vacantSlot.addSlot(id));
        return vacantSlots;
    }

    @Override
    public List<ParkingTicket> getAllOccupied() {
        List<ParkingTicket> tickets = occupiedParkingSlots.values().stream().map(x -> new SimpleParkingTicket(x.getId(), x.getVehicle())).collect(Collectors.toList());
        tickets.forEach(x->x.addSlot(id));
        return tickets;
    }

    @Override
    public ParkingTicket park(Vehicle vehicle) {
        log.debug("Parking :: " + vehicle);
        AbstractParkingSlot parkingSlot = selectionStrategy.nextSlot(vehicle);
        parkingSlot.park(vehicle);
        ParkingTicket parkingTicket = new SimpleParkingTicket(parkingSlot.getId(), vehicle);
        occupiedParkingSlots.put(parkingTicket.getSlots().get(0), parkingSlot);
        parkingTicket.addSlot(id);
        log.debug("Issuing ticket :: " + parkingTicket);
        return parkingTicket;
    }

    @Override
    public Vehicle free(List<Integer> slots){
        log.debug("releasing :: " + slots);
        AbstractParkingSlot parkingSlot = occupiedParkingSlots.remove(slots.get(0));
        selectionStrategy.addSlot(parkingSlot);
        return parkingSlot.free();
    }

    private void createSlots(int count, ParkingSlotFactory parkingSlotFactory, String type){
        for(int i = 0; i <count; i++){
            selectionStrategy.addSlot(parkingSlotFactory.createSlot(type, i+1));
        }
    }
}
