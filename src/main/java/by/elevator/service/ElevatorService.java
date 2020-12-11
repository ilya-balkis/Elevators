package by.elevator.service;

import by.elevator.entity.building.Building;
import by.elevator.entity.direction.Direction;
import by.elevator.entity.elevator.impl.PassengerElevator;
import by.elevator.entity.floor.Floor;
import by.elevator.entity.human.impl.Passenger;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ElevatorService {

    private final Building building;

    private ElevatorService(Building building) {
        this.building = building;
    }

    public static ElevatorService of(Building building) {
        return new ElevatorService(building);
    }

    public boolean checkPassengers() {

        boolean existed = checkElevator(building.getElevators());

        for (Map.Entry<Integer, Floor> floorMap : building.getFloors().entrySet()) {
            Floor floor = floorMap.getValue();

            List<Passenger> upQueue = floor.getUpQueue();
            List<Passenger> downQueue = floor.getDownQueue();

            boolean existedUp = checkQueue(upQueue);
            boolean existedDown = checkQueue(downQueue);

            if (!existedUp || !existedDown) {
                existed = true;
                break;
            }
        }

        return existed;
    }

    private boolean checkElevator(List<PassengerElevator> elevators){

        boolean result = false;

        for(PassengerElevator elevator : elevators){
            if (!elevator.getPassengers().isEmpty()){
                result = true;
            }
        }

        return result;
    }

    private boolean checkQueue(List<Passenger> passengers) {
        return passengers.isEmpty();
    }

    public void move(PassengerElevator elevator) throws InterruptedException {

        Direction currentDirection = elevator.getDirection();
        int currentFloor = elevator.getCurrentFloor();

        Direction newDirection = adjustDirection(currentFloor, currentDirection);
        elevator.setDirection(newDirection);

        List<Passenger> upQueue = building.getFloors().get(currentFloor).getUpQueue();
        List<Passenger> downQueue = building.getFloors().get(currentFloor).getDownQueue();

        log.info("\n");
        log.info("*****INFO {} ELEVATOR****", elevator.getIdentificationCode());
        log.info("Passengers in elevator: {}", elevator.getPassengers());
        log.info("Free weight space: {}", elevator.getCarrying() - elevator.getTotalWeight());
        log.info("Current floor: {}", currentFloor);
        log.info("Queue up: {}", upQueue);
        log.info("Queue down: {}", downQueue);
        log.info("************************");

        sleepElevatorMovement(elevator.getLiftTime());

        if (newDirection.equals(Direction.GOING_UP)) {
            if (pickPassengers(upQueue, elevator) || removePassengers(elevator)) {
                log.info(">Doors are open.<");
                sleepDoorMovement(elevator.getClosingTime());
                log.info(">Doors are closed.<");
            }
            elevator.goUp();
        } else if (newDirection.equals(Direction.GOING_DOWN)) {
            if (pickPassengers(downQueue, elevator) || removePassengers(elevator)) {
                log.info(">Doors are open.<");
                sleepDoorMovement(elevator.getClosingTime());
                log.info(">Doors are closed.<");
            }
            elevator.goDown();
        }

        log.info("************************");
        log.info("Next floor: {}", elevator.getCurrentFloor());
        log.info("Passengers in elevator: {}", elevator.getPassengers());
        log.info("Free weight space: {}", elevator.getCarrying() - elevator.getTotalWeight());
        log.info("************************");
        log.info("\n");
    }

    private void sleepDoorMovement(int time) throws InterruptedException {
        TimeUnit.SECONDS.sleep(time);
    }

    private void sleepElevatorMovement(int time) throws InterruptedException {
        TimeUnit.SECONDS.sleep(time);
    }

    //////////////
    // new test //
    /////////////

    private boolean pickPassengers(List<Passenger> passengers, PassengerElevator elevator) {

        boolean result = false;

        for (int i = 0; i < passengers.size(); ) {

            Passenger passenger = passengers.get(i);

            if (!elevator.putPassenger(passenger)) {
                break;
            }

            passengers.remove(i);
            result = true;
            StatisticService.addNote(elevator.getIdentificationCode(), passenger.toString());
            StatisticService.addNote(elevator.getIdentificationCode(), elevator.getAmountOfTransportedPassengers());
        }

        return result;
    }

    private boolean removePassengers(PassengerElevator elevator) {

        boolean result = false;

        List<Passenger> passengers = elevator.getPassengers();
        int currentFloor = elevator.getCurrentFloor();

        int i = 0;

        while (i < passengers.size()) {
            Passenger passenger = passengers.get(i);

            int desiredFloor = passenger.getDesiredFloor();

            if (desiredFloor == currentFloor) {
                elevator.deletePassenger(passenger);
                result = true;
            } else {
                i++;
            }
        }

        return result;

    }

    private Direction adjustDirection(int currentFloor, Direction direction) {

        int lastFloor = building.getAmountOfFloors();
        int firstFloor = 1;

        Direction newDirection;

        if (currentFloor == lastFloor) {
            newDirection = Direction.GOING_DOWN;
        } else if (currentFloor == firstFloor) {
            newDirection = Direction.GOING_UP;
        } else {
            newDirection = direction;
        }

        return newDirection;
    }

}
