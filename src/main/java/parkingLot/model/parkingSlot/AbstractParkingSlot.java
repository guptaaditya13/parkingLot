package parkingLot.model.parkingSlot;

import parkingLot.model.vehicle.Vehicle;

public interface AbstractParkingSlot {
    void park(Vehicle vehicle);
    Vehicle free();
    Vehicle getVehicle();
    int getId();
}
