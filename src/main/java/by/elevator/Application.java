package by.elevator;

import by.elevator.controller.MovementController;
import by.elevator.creator.building_creator.impl.BuildingCreator;
import by.elevator.entity.building.Building;
import by.elevator.entity.elevator.impl.PassengerElevator;
import by.elevator.executer.ElevatorExecutor;
import by.elevator.generator.PassengersGenerator;
import by.elevator.service.ElevatorService;
import by.elevator.service.StatisticService;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Application {

    public static void main(String[] args) throws InterruptedException {

        Building building = new BuildingCreator().create();
        ElevatorService service = ElevatorService.of(building);
        PassengersGenerator generator = new PassengersGenerator();
        MovementController movementController = new MovementController();
        StatisticService.createFile();

        List<ElevatorExecutor> executors = new LinkedList<>();
        List<PassengerElevator> elevators = building.getElevators();

        for (PassengerElevator elevator : elevators) {
            executors.add(new ElevatorExecutor(elevator, service));
        }

        executors.forEach(ElevatorExecutor::start);

        generator.generate(building);

        while (true) {

            if (service.checkPassengers()) {
                movementController.startElevators();
            } else {
                movementController.startElevators();
            }

            TimeUnit.SECONDS.sleep(15);
            generator.generate(building);
        }
    }
}
