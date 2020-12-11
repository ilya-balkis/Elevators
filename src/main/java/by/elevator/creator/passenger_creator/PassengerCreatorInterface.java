package by.elevator.creator.passenger_creator;

import by.elevator.entity.human.impl.Passenger;

public interface PassengerCreatorInterface {

    Passenger create(int amountOfFloors);
}
