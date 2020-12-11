package by.elevator.creator.passenger_creator.impl;

import by.elevator.creator.passenger_creator.PassengerCreatorInterface;
import by.elevator.entity.human.impl.Passenger;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;

@Slf4j
public class PassengerCreator implements PassengerCreatorInterface {

    private static final List<String> NAMES =
            List.of("Abram", "Sara", "Adward",
                    "Clava", "Veniamin", "Boris",
                    "Avram", "Asa", "Semion", "Aleksander",
                    "Elazar", "Kate", "Gild", "Solomon",
                    "Yakov", "Mark", "Jacob", "Elena",
                    "Kirill", "Artur");

    private static final double MIN_WEIGHT = 40;
    private static final double MAX_WEIGHT = 160;

    private static final int MIN_NUMBER_OF_FLOOR = 1;

    private static final Random RANDOM = new Random();

    @Override
    public Passenger create(int amountOfFloors) {

        log.info("***Creator of passenger***");

        String name = createName();
        double weight = createWeight();

        int currentFloor = createRandomFloor(amountOfFloors);
        int desiredFloor;

        do {
            desiredFloor = createRandomFloor(amountOfFloors);
        }
        while (currentFloor == desiredFloor);

        return Passenger.of(name, weight, desiredFloor, currentFloor);
    }

    private String createName() {
        int i = RANDOM.nextInt(NAMES.size());

        return NAMES.get(i);
    }

    private int createRandomFloor(int amountOfFloors) {
        return MIN_NUMBER_OF_FLOOR + RANDOM.nextInt(amountOfFloors);
    }

    private double createWeight() {
        return MIN_WEIGHT + Math.random() * (MAX_WEIGHT);
    }
}
