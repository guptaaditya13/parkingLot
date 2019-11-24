package parkingLot.model.ticket;

import parkingLot.model.vehicle.Vehicle;


public class SimpleTicket extends UnassignedTicket {
    private Vehicle vehicle;

    public SimpleTicket(int slot, Vehicle vehicle) {
        super(slot);
        this.vehicle = vehicle;
    }

    @Override
    public Vehicle getVehicle() {
        return this.vehicle;
    }

    @Override
    public String toString() {
        return vehicle == null ? getCondensedSlot() : getCondensedSlot() + "::" + vehicle;
    }
}
