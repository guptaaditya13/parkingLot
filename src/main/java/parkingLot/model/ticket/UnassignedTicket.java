package parkingLot.model.ticket;

import parkingLot.model.vehicle.Vehicle;

import java.util.LinkedList;
import java.util.List;

public class UnassignedTicket implements Ticket {
    private final List<Integer> slots;

    public UnassignedTicket(int slot) {
        this.slots = new LinkedList<>();
        this.slots.add(slot);
    }

    @Override
    public String getCondensedSlot() {
        return Ticket.getCondensedSlot(slots);
    }

    @Override
    public void addSlot(int slot) {
        slots.add(0,slot);
    }

    @Override
    public List<Integer> getSlots() {
        return slots;
    }

    @Override
    public Vehicle getVehicle() {
        return null;
    }

    @Override
    public String toString() {
        return getCondensedSlot();
    }
}
