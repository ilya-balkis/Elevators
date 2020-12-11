package by.elevator.creator.building_creator.impl;

import by.elevator.creator.building_creator.BuildingCreatorInterface;
import by.elevator.entity.building.Building;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class BuildingCreator implements BuildingCreatorInterface {

    @Override
    public Building create() {
        Scanner scanner = new Scanner(System.in);

        log.info("***Creator of building***");

        log.info("Input number of floors: ");
        int numberOfFloors = scanner.nextInt();
        log.info("Input number of elevators: ");
        int numberOfElevators = scanner.nextInt();

        return Building.of(numberOfFloors, numberOfElevators);
    }

}
