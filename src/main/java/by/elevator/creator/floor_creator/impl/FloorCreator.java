package by.elevator.creator.floor_creator.impl;

import by.elevator.creator.floor_creator.FloorCreatorInterface;
import by.elevator.entity.floor.Floor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class FloorCreator implements FloorCreatorInterface {

    public Map<Integer, Floor> createAll(int amountOfFloors) {

        log.info("***Creator of floor***");

        Map<Integer, Floor> floors = new HashMap<>();

        for (int i = 1; i <= amountOfFloors; i++) {
            floors.put(i, Floor.of(i));
        }

        return floors;
    }
}
