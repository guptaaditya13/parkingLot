package parkingLot.model.level;

import org.apache.log4j.Logger;
import parkingLot.Exception.ParkingSpaceEmptyException;
import parkingLot.Exception.ParkingSpaceFullException;
import parkingLot.Exception.UnknownParkingSpaceException;
import parkingLot.model.slot.AbstractSlot;
import parkingLot.model.slot.SlotFactory;
import parkingLot.model.level.selectionStrategy.SelectionStrategy;
import parkingLot.model.ticket.Ticket;
import parkingLot.model.ticket.SimpleTicket;
import parkingLot.model.vehicle.Vehicle;

import java.util.*;
import java.util.stream.Collectors;

class SimpleLevel implements AbstractLevel {
    private final int id;
    private final Map<Integer, AbstractSlot> occupiedParkingSlots;
    private SelectionStrategy selectionStrategy;
    private static final Logger log = Logger.getLogger(SimpleLevel.class);
    private static int parkingSlots;


    public SimpleLevel(int id, int parkingSlots, SelectionStrategy selectionStrategy, SlotFactory slotFactory, String slotType) {
        this.id = id;
        occupiedParkingSlots = new HashMap<>(parkingSlots);
        this.selectionStrategy = selectionStrategy;
        createSlots(parkingSlots, slotFactory, slotType);
        this.parkingSlots = parkingSlots;
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
    public List<Ticket> getAllVacantSlots() {
        List<Ticket> vacantSlots = selectionStrategy.getAllVacantSlots();
        vacantSlots.forEach(vacantSlot -> vacantSlot.addSlot(id));
        return vacantSlots;
    }

    @Override
    public List<Ticket> getAllOccupied() {
        List<Ticket> tickets = occupiedParkingSlots.values().stream().map(x -> new SimpleTicket(x.getId(), x.getVehicle())).collect(Collectors.toList());
        tickets.forEach(x->x.addSlot(id));
        return tickets;
    }

    @Override
    public Ticket park(Vehicle vehicle) throws ParkingSpaceFullException{
        log.debug("Parking :: " + vehicle);
        AbstractSlot parkingSlot = selectionStrategy.nextSlot(vehicle);
        parkingSlot.park(vehicle);
        Ticket ticket = new SimpleTicket(parkingSlot.getId(), vehicle);
        occupiedParkingSlots.put(ticket.getSlots().get(0), parkingSlot);
        ticket.addSlot(id);
        log.debug("Issuing ticket :: " + ticket);
        return ticket;
    }

    @Override
    public Vehicle free(List<Integer> slots) throws UnknownParkingSpaceException, ParkingSpaceEmptyException {
        log.debug("releasing :: " + slots);
        int key = slots.get(0);
        if (occupiedParkingSlots.containsKey(key)) {
            AbstractSlot parkingSlot = occupiedParkingSlots.remove(key);
            selectionStrategy.addSlot(parkingSlot);
            return parkingSlot.free();
        } else {
            if (key <= parkingSlots && key > 0){
                throw new ParkingSpaceEmptyException();
            }
            throw new UnknownParkingSpaceException();
        }
    }

    private void createSlots(int count, SlotFactory slotFactory, String type){
        for(int i = 0; i <count; i++){
            selectionStrategy.addSlot(slotFactory.createSlot(type, i+1));
        }
    }
}
