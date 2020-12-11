package by.elevator.creator.passenger_creator;

import by.elevator.creator.passenger_creator.impl.PassengerCreator;
import by.elevator.entity.human.impl.Passenger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class PassengerCreatorTest {

    private static PassengerCreator passengerCreator;

    @BeforeAll
    public static void init() {
        passengerCreator = new PassengerCreator();
    }

    /*
     * Check correct passenger instance creation
     */
    @Test
    @DisplayName("Create passenger with different desired floor and current floor")
    void create_currentFlourEquivalentDesiredFloor_passenger() {
        int amountOfPassenger = 5;

        Passenger passenger = passengerCreator.create(amountOfPassenger);

        assertNotNull(passenger);
    }

//    /*
//     * Check situation with equal generation of current floor and desired floor
//     */
//    @Test
//    @DisplayName("Create passenger with same desired floor and current floor")
//    void create_currentFlourNotEquivalentDesiredFloor_passenger() {
//        int amountOfPassenger = 5;
//        PassengerCreator mock = spy(new PassengerCreator());
//        verifyPrivate(mock).
//
//        creator.create();
//    }
}
