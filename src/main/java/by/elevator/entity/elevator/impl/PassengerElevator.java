package by.elevator.entity.elevator.impl;

import by.elevator.entity.direction.Direction;
import by.elevator.entity.direction.State;
import by.elevator.entity.elevator.ElevatorInterface;
import by.elevator.entity.human.Human;
import by.elevator.entity.human.impl.Passenger;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@EqualsAndHashCode
@ToString
public class PassengerElevator implements ElevatorInterface {

    @Setter
    private List<Passenger> passengers;
    private final String identificationCode;
    private final double carrying;
    private final int liftTime; // time for lifting 1 floor
    private final int closingTime; // time for closing doors
    private Direction direction;
    private int amountOfTransportedPassengers;
    private int currentFloor;

    private PassengerElevator(double carrying, int liftTime, int closingTime, int currentFloor) {
        this.passengers = new ArrayList<>();
        this.carrying = carrying;
        this.liftTime = liftTime;
        this.closingTime = closingTime;
        this.currentFloor = currentFloor;
        direction = Direction.GOING_UP;
        identificationCode = UUID.randomUUID().toString();
        amountOfTransportedPassengers = 0;
    }

    public static PassengerElevator of(double carrying, int liftTime, int closingTime, int currentFloor) {
        return new PassengerElevator(carrying, liftTime, closingTime, currentFloor);
    }

    public boolean putPassenger(Passenger passenger) {
        boolean result = false;

        double totalWeight = getTotalWeight();
        double weight = totalWeight + passenger.getWeight();

        if (weight <= carrying) {
            passengers.add(passenger);
            result = true;
            amountOfTransportedPassengers++;
        }

        return result;
    }

    public double getTotalWeight() {
        return passengers.stream().mapToDouble(Human::getWeight).sum();
    }

    public boolean deletePassenger(Passenger passenger) {
        boolean result = false;

        if (passengers.remove(passenger)) {
            result = true;
        }

        return result;
    }

    @Override
    public void goUp() {
        currentFloor++;
    }

    @Override
    public void goDown() {
        currentFloor--;
    }

    @Override
    public void callUp() {
        direction = Direction.GOING_UP;
    }

    @Override
    public void callDown() {
        direction = Direction.GOING_DOWN;
    }
}
