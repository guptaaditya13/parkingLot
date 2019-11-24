package parkingLot.model.ticket;

import parkingLot.model.vehicle.Vehicle;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface Ticket {
    String getCondensedSlot();
    void addSlot(int slot);
    List<Integer> getSlots();
    Vehicle getVehicle();
    static List<Integer> condensedSlotToList(String condensedSlot){
        return Arrays.stream(condensedSlot.split("-")).map(Integer::parseInt).collect(Collectors.toList());
    }
    static String getCondensedSlot(List<Integer> slots){
        return slots.stream().map(String::valueOf).collect(Collectors.joining("-"));
    }
}
