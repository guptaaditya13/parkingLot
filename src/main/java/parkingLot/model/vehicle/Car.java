package parkingLot.model.vehicle;

public class Car implements Vehicle {
    private final String numberPlate;
    private final String color;

    public Car(String numberPlate, String color) {
        this.numberPlate = numberPlate;
        this.color = color;
    }

    @Override
    public String getNumber() {
        return null;
    }

    @Override
    public String getColour() {
        return null;
    }

    @Override
    public String toString() {
        return "Car{" +
                "numberPlate='" + numberPlate + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
