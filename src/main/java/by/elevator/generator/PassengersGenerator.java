package by.elevator.generator;

import by.elevator.creator.passenger_creator.impl.PassengerCreator;
import by.elevator.entity.building.Building;
import by.elevator.entity.floor.Floor;
import by.elevator.entity.human.impl.Passenger;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


@Slf4j
public class PassengersGenerator {

    private final static int MAX_AMOUNT_OF_GENERATED_PASSENGERS = 25;
    private final static int MIN_AMOUNT_OF_GENERATED_PASSENGERS = 1;

    private final static Random RANDOM = new Random();

    public void generate(Building building) {

        log.info("###New passengers generation###");

        int numberOfGeneratedPassengers = generateNumber();
        int amountOfFlours = building.getAmountOfFloors();

        List<Passenger> passengers = generatePassengers(amountOfFlours, numberOfGeneratedPassengers);
        Map<Integer, Floor> floors = building.getFloors();
        distributePassengers(floors, passengers);
    }

    private List<Passenger> generatePassengers(int amountOfFloors, int numberOfGeneratedPassengers) {

        List<Passenger> generatedPassengers = new ArrayList<>();
        PassengerCreator creator = new PassengerCreator();

        for (int i = 0; i < numberOfGeneratedPassengers; i++) {
            Passenger passenger = creator.create(amountOfFloors);
            generatedPassengers.add(passenger);
        }

        return generatedPassengers;
    }

    private void distributePassengers(Map<Integer, Floor> floors, List<Passenger> passengers) {

        for (Map.Entry<Integer, Floor> floorMap : floors.entrySet()) {
            Floor floor = floorMap.getValue();
            for (Passenger passenger : passengers) {
                if (floor.getNumber() == passenger.getCurrentFloor()) {
                    if (passenger.getCurrentFloor() - passenger.getDesiredFloor() > 0) {
                        floor.getDownQueue().add(passenger);
                    } else {
                        floor.getUpQueue().add(passenger);
                    }
                }
            }
        }
    }

    private int generateNumber() {
        return MIN_AMOUNT_OF_GENERATED_PASSENGERS + RANDOM.nextInt(MAX_AMOUNT_OF_GENERATED_PASSENGERS);
    }
}
