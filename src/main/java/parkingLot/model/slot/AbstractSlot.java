package parkingLot.model.slot;

import parkingLot.Exception.ParkingSpaceEmptyException;
import parkingLot.Exception.ParkingSpaceFullException;
import parkingLot.model.vehicle.Vehicle;

public interface AbstractSlot {
    void park(Vehicle vehicle) throws ParkingSpaceFullException;
    Vehicle free() throws ParkingSpaceEmptyException;
    Vehicle getVehicle();
    int getId();
}
