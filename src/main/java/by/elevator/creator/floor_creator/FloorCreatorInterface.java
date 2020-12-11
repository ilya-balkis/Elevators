package by.elevator.creator.floor_creator;

import by.elevator.entity.floor.Floor;

import java.util.Map;

public interface FloorCreatorInterface {

    Map<Integer, Floor> createAll(int amountOfFloors);
}
