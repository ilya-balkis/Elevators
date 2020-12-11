package by.elevator.entity.direction;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum Direction {
    GOING_UP("up"),
    GOING_DOWN("down");

    private final String direction;

    Direction(String direction) {
        this.direction = direction;
    }
}
