package parkingLot.model.parkingTicket;

import parkingLot.model.vehicle.Vehicle;


public class SimpleParkingTicket extends UnassignedTicket {
    private Vehicle vehicle;

    public SimpleParkingTicket(int slot, Vehicle vehicle) {
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
