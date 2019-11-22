package parkingLot.model.vehicle;

public class Vehicle {
    private final String number;
    private final String colour;

    public Vehicle(String number, String colour) {
        this.number = number;
        this.colour = colour;
    }

    public String getNumber() {
        return number;
    }

    public String getColour() {
        return colour;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "number='" + number + '\'' +
                ", colour='" + colour + '\'' +
                '}';
    }
}
