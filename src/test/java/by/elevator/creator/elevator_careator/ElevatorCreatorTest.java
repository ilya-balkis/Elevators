package by.elevator.creator.elevator_careator;

import by.elevator.creator.elevator_creator.impl.ElevatorCreator;
import by.elevator.entity.elevator.impl.PassengerElevator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ElevatorCreatorTest {

    private static ElevatorCreator creator;

    @BeforeAll
    public static void init() {
        creator = new ElevatorCreator();
    }

    /*
     * Check passenger elevator instance creation
     */
    @Test
    @DisplayName("Create simple passenger elevator")
    void create_createPassengerElevators_passengerElevator() {
        int amountOfElevators = 3;

        PassengerElevator elevator = creator.create(amountOfElevators);

        assertNotNull(elevator);
    }

    /*
     * Check passenger elevator list creation
     */
    @Test
    @DisplayName("Create list of passenger elevators")
    void createAll_createPassengerElevators_passengerElevators() {
        int expectedAmountOfElevators = 3;
        int amountOfFloors = 3;

        List<PassengerElevator> elevators = creator.createAll(expectedAmountOfElevators, amountOfFloors);

        int resultAmountOfElevators = elevators.size();

        assertEquals(expectedAmountOfElevators, resultAmountOfElevators);
    }

    /*
     * Check correct work with negative amount of elevators
     */
    @Test
    @DisplayName("Create list if passenger elevator with negative amount of elevators")
    void createAll_createPassengerElevators_emptyListOfPassengersElevators() {
        int amountOfElevators = -1;
        int amountOfFloors = 3;

        List<PassengerElevator> elevators = creator.createAll(amountOfElevators, amountOfFloors);

        int expectedAmountOfElevators = 0;
        int resultAmountOfElevators = elevators.size();

        assertEquals(expectedAmountOfElevators, resultAmountOfElevators);
    }
}
