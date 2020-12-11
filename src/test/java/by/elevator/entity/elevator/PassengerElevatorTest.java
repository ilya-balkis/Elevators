package by.elevator.entity.elevator;

import by.elevator.entity.elevator.impl.PassengerElevator;
import by.elevator.entity.human.impl.Passenger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassengerElevatorTest {

    /*
     * When you try to add a passenger with a weight of 240
     * to an elevator with a weight capacity of 300
     * this method will return false.
     */
    @Test
    @DisplayName("Try to add passenger with suitable weight")
    void putPassenger_addPassengerWithSuitableWeight_true() {
        Passenger passenger = Passenger.of("Ilya", 240.0, 3, 1);
        PassengerElevator passengerElevator = PassengerElevator.of(300.0, 5, 5, 4);

        assertTrue(passengerElevator.putPassenger(passenger));
    }

    /*
     * When you try to add a passenger with a weight of 240
     * to an elevator with a weight capacity of 100
     * this method will return true.
     */
    @Test
    @DisplayName("Try to add passenger with unsuitable weight")
    void putPassenger_addPassengerWithUnsuitableWeight_false() {
        Passenger passenger = Passenger.of("Ilya", 240.0, 3, 1);
        PassengerElevator passengerElevator = PassengerElevator.of(100.0, 5, 5, 4);

        assertFalse(passengerElevator.putPassenger(passenger));
    }

    /*
     * When adding passengers with weight 110 and 15,
     * this method will return 125.
     */
    @Test
    @DisplayName("Check counting of weight")
    void getTotalWeight_countTotalPassengersWeight_125() {
        Passenger firstPassenger = Passenger.of("Ilya", 110.0, 3, 1);
        Passenger secondPassenger = Passenger.of("Alex", 15.0, 7, 5);
        double expectedCarrying = 125.0;
        PassengerElevator passengerElevator = PassengerElevator.of(1000, 5, 5, 4);
        passengerElevator.putPassenger(firstPassenger);
        passengerElevator.putPassenger(secondPassenger);

        double actualCarrying = passengerElevator.getTotalWeight();

        assertEquals(expectedCarrying, actualCarrying);
    }

    /*
     * When trying to delete a passenger who is in the elevator,
     * this method will return true.
     */
    @Test
    @DisplayName("Try to delete user that is in elevator")
    void deletePassenger_deleteExistingUser_true() {
        Passenger passenger = Passenger.of("Ilya", 240.0, 3, 1);
        PassengerElevator passengerElevator = PassengerElevator.of(300.0, 5, 5, 4);
        passengerElevator.putPassenger(passenger);

        assertTrue(passengerElevator.deletePassenger(passenger));
    }

    /*
     * When trying to delete a passenger who is not in the elevator,
     * this method will return false.
     */
    @Test
    @DisplayName("Try to delete user that is not in elevator")
    void deletePassenger_deleteNotExistingUser_false() {
        Passenger passenger = Passenger.of("Ilya", 240.0, 3, 1);
        Passenger fakePassenger = Passenger.of("Fake", 240.0, 3, 1);
        PassengerElevator passengerElevator = PassengerElevator.of(300.0, 5, 5, 4);
        passengerElevator.putPassenger(passenger);

        assertFalse(passengerElevator.deletePassenger(fakePassenger));
    }

}