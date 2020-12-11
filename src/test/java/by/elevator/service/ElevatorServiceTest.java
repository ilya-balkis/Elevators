package by.elevator.service;

import by.elevator.entity.building.Building;
import by.elevator.entity.human.impl.Passenger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ElevatorServiceTest {

    /*
     * Checking the search of passengers on empty floors.
    */
    @Test
    @DisplayName("Check passengers on floors, when no one is waiting elevator")
    void checkPassengers_checkPassengersOnEmptyFloors_false() {
        int amountOfFloors = 5;
        int amountOfElevators = 1;
        Building building = Building.of(amountOfFloors, amountOfElevators);
        ElevatorService service = ElevatorService.of(building);

        boolean result = service.checkPassengers();

        assertFalse(result);
    }

    /*
    * Check passengers on floors, when someone is waiting elevator for go down
    */
    @Test
    @DisplayName("Check passengers on floors, when someone is waiting elevator for go down")
    void checkPassengers_checkGoingDownPassengersOnFloors_true() {
        int amountOfFloors = 5;
        int amountOfElevators = 1;
        Building building = Building.of(amountOfFloors, amountOfElevators);
        Passenger passenger = Passenger.of("name", 1, 2, 3);
        building.getFloors().get(1).getDownQueue().add(passenger);
        ElevatorService service = ElevatorService.of(building);

        boolean result = service.checkPassengers();

        assertTrue(result);
    }

    /*
     * Check passengers on floors, when someone is waiting elevator for go up
     */
    @Test
    @DisplayName("Check passengers on floors, when someone is waiting elevator for go up")
    void checkPassengers_checkGoingUpPassengersOnFloors_true() {
        int amountOfFloors = 5;
        int amountOfElevators = 1;
        Building building = Building.of(amountOfFloors, amountOfElevators);
        Passenger passenger = Passenger.of("name", 1, 2, 1);
        building.getFloors().get(1).getUpQueue().add(passenger);
        ElevatorService service = ElevatorService.of(building);

        boolean result = service.checkPassengers();

        assertTrue(result);
    }
}
