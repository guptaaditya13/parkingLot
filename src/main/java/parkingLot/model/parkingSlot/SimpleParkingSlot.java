package parkingLot.model.parkingSlot;

import org.apache.log4j.Logger;
import parkingLot.Exceptions.ParkingSpaceEmptyException;
import parkingLot.Exceptions.ParkingSpaceFullException;
import parkingLot.model.vehicle.Vehicle;

class SimpleParkingSlot implements AbstractParkingSlot {
    private final int slotId;
    private Vehicle parkedVehicle;
    private static final Logger log = Logger.getLogger(SimpleParkingSlot.class);


    public SimpleParkingSlot(int slotId) {
        this.slotId = slotId;
        parkedVehicle = null;
    }

    @Override
    public Vehicle free() throws ParkingSpaceEmptyException{
        if (parkedVehicle != null) {
            Vehicle vehicle = this.parkedVehicle;
            this.parkedVehicle = null;
            log.debug("released :: " + vehicle);
            return vehicle;
        } else {
            throw new ParkingSpaceEmptyException();
        }
    }

    @Override
    public Vehicle getVehicle() {
        return parkedVehicle;
    }

    @Override
    public int getId() {
        return slotId;
    }

    public void park(Vehicle vehicle) throws ParkingSpaceFullException {
        if (this.parkedVehicle == null) {
            this.parkedVehicle = vehicle;
            log.debug("parked :: " + parkedVehicle);
        } else {
            throw new ParkingSpaceFullException();
        }
    }
}
