package parkingLot.model.parkingSlot;

import org.apache.log4j.Logger;
import parkingLot.model.parkingTicket.ParkingTicket;
import parkingLot.model.parkingTicket.SimpleParkingTicket;
import parkingLot.model.vehicle.Vehicle;

class SimpleParkingSlot implements AbstractParkingSlot{
    private final int slotId;
    private Vehicle parkedVehicle;
    private static final Logger log = Logger.getLogger(SimpleParkingSlot.class);


    public SimpleParkingSlot(int slotId) {
        this.slotId = slotId;
        parkedVehicle = null;
    }
    @Override
    public Vehicle free(){
        Vehicle vehicle = this.parkedVehicle;
        this.parkedVehicle = null;
        log.debug("released :: " + vehicle);
        return vehicle;
    }

    @Override
    public Vehicle getVehicle() {
        return parkedVehicle;
    }

    @Override
    public int getId() {
        return slotId;
    }

    public void park(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        log.debug("parked :: " + parkedVehicle);
    }
}
