package parkingLot.model.parkingSlot;

import parkingLot.Exceptions.ParkingSpaceEmptyException;
import parkingLot.Exceptions.ParkingSpaceFullException;
import parkingLot.model.vehicle.Vehicle;

public interface AbstractParkingSlot {
    void park(Vehicle vehicle) throws ParkingSpaceFullException;
    Vehicle free() throws ParkingSpaceEmptyException;
    Vehicle getVehicle();
    int getId();
}
