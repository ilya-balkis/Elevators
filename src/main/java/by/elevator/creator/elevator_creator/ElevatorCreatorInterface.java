package by.elevator.creator.elevator_creator;

import by.elevator.entity.elevator.ElevatorInterface;
import by.elevator.entity.elevator.impl.PassengerElevator;

import java.util.List;

public interface ElevatorCreatorInterface {

    List<PassengerElevator> createAll(int amountOfElevators, int amountOfFloors);

    ElevatorInterface create(int amountOfFloors);
}
