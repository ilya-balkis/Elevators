package by.elevator.creator.floor_creator;

import by.elevator.creator.floor_creator.impl.FloorCreator;
import by.elevator.entity.floor.Floor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FloorCreatorTest {

    private static FloorCreator floorCreator;

    @BeforeAll
    public static void setUp() {
        floorCreator = new FloorCreator();
    }

    /*
     * When you try to create a building with 0 or less floors,
     * this method will return an empty Map
     */
    @Test
    @DisplayName("Try to create building with 0 floors")
    void createAll_createBuildingWithZeroFloors_emptyMap() {

        Map<Integer, Floor> actualFloors = floorCreator.createAll(0);

        assertTrue(actualFloors.isEmpty());
    }

    /*
     * When you try to create a building with 1 or more floors,
     * this method will return a Map with Floor objects.
     */
    @Test
    @DisplayName("Try to create building with 5 floors")
    void createAll_createBuildingWithFiveFloors_emptyMap() {
        int expectedSize = 5;

        Map<Integer, Floor> actualFloors = floorCreator.createAll(5);

        assertEquals(expectedSize, actualFloors.size());
    }
}