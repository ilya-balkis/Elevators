package by.elevator.creator.elevator_creator.impl;

import by.elevator.creator.elevator_creator.ElevatorCreatorInterface;
import by.elevator.entity.elevator.impl.PassengerElevator;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class ElevatorCreator implements ElevatorCreatorInterface {

    private static final double MIN_CARRYING = 400;
    private static final double MAX_CARRYING = 1000;

    private static final int MIN_LIFT_TIME = 5;
    private static final int MAX_LIFT_TIME = 10;

    private static final int MIN_DOOR_TIME = 1;
    private static final int MAX_DOOR_TIME = 3;

    private static final int FIRST_FLOOR = 1;

    private static final Random RANDOM = new Random();

    @Override
    public List<PassengerElevator> createAll(int amountOfElevators, int amountOfFloors) {
        return IntStream.rangeClosed(1, amountOfElevators)
                .mapToObj(s -> create(amountOfFloors))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public PassengerElevator create(int amountOfElevators) {

        log.info("***Creator of elevator***");

        double carrying = generateCarrying();
        int liftTime = generateLiftTime();
        int closingTime = generateClosingTime();
        int currentFloor = generateCurrentFloor(amountOfElevators);

        return PassengerElevator.of(carrying, liftTime, closingTime, currentFloor);
    }

    private double generateCarrying() {
        return MIN_CARRYING + Math.random() * MAX_CARRYING;
    }

    private int generateLiftTime() {
        return MIN_LIFT_TIME + RANDOM.nextInt(MAX_LIFT_TIME);
    }

    private int generateClosingTime() {
        return MIN_DOOR_TIME + RANDOM.nextInt(MAX_DOOR_TIME);
    }

    private int generateCurrentFloor(int amountOfFloors) {
        return FIRST_FLOOR + RANDOM.nextInt(amountOfFloors);
    }
}
