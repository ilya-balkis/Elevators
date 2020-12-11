package by.elevator.entity.floor;

import by.elevator.entity.human.impl.Passenger;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
public class Floor {

    private final int number;
    private final List<Passenger> upQueue;
    private final List<Passenger> downQueue;

    private Floor(int number) {
        this.number = number;
        this.upQueue = new ArrayList<>();
        this.downQueue = new ArrayList<>();
    }

    public static Floor of(int number) {
        return new Floor(number);
    }
}
