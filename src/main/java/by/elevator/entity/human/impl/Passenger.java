package by.elevator.entity.human.impl;

import by.elevator.entity.human.Human;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class Passenger extends Human {

    private final int currentFloor;
    private final int desiredFloor;

    private Passenger(String name, double weight, int desiredFloor, int currentFloor) {
        super(name, weight);
        this.desiredFloor = desiredFloor;
        this.currentFloor = currentFloor;
    }

    public static Passenger of(String name, double weight, int desiredFloor, int currentFloor) {
        return new Passenger(name, weight, desiredFloor, currentFloor);
    }

    @Override
    public String toString() {
        return "Passenger: " + super.getName() +
                ". From: " + currentFloor +
                " floor. To: " + desiredFloor + " floor.";
    }
}
