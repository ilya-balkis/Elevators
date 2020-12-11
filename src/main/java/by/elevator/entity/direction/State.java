package by.elevator.entity.direction;

public enum State {
    MOVEMENT("move"),
    WAITING("wait");

    private final String state;

    State(String direction) {
        this.state = direction;
    }
}
