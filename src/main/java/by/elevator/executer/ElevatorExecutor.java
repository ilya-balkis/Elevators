package by.elevator.executer;

import by.elevator.entity.elevator.impl.PassengerElevator;
import by.elevator.service.ElevatorService;
import lombok.SneakyThrows;

public class ElevatorExecutor extends Thread {

    private boolean stopped;
    private final PassengerElevator elevator;
    private final ElevatorService service;

    public ElevatorExecutor(PassengerElevator elevator, ElevatorService service) {
        this.stopped = true;
        this.elevator = elevator;
        this.service = service;
    }

    @Override
    @SneakyThrows
    public void run() {
        while(stopped){
            service.move(elevator);
        }
    }

    public void stopElevator(){
        stopped = false;
    }
}
