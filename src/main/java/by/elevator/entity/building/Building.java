package by.elevator.entity.building;

import by.elevator.creator.elevator_creator.impl.ElevatorCreator;
import by.elevator.creator.floor_creator.impl.FloorCreator;
import by.elevator.entity.elevator.impl.PassengerElevator;
import by.elevator.entity.floor.Floor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@EqualsAndHashCode
@ToString
public class Building {

    private final int amountOfFloors;
    private final int amountOfElevators;
    private final List<PassengerElevator> elevators;
    private final Map<Integer, Floor> floors;

    private Building(int amountOfFloors, int amountOfElevators) {
        this.amountOfFloors = amountOfFloors;
        this.amountOfElevators = amountOfElevators;
        this.elevators = new ElevatorCreator().createAll(amountOfElevators, amountOfFloors);
        this.floors = new FloorCreator().createAll(amountOfFloors);
    }

    public static Building of(int numberOfFloors, int amountOfElevators) {
        return new Building(numberOfFloors, amountOfElevators);
    }
}
