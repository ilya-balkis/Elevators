package by.elevator.entity.human;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public abstract class Human {

    private final String name;
    private final double weight;

    public Human(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

}
