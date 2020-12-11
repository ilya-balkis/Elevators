package by.elevator.controller;

public class MovementController {

    private boolean flag = true;

    public synchronized void startElevators() {
        flag = true;
        notifyAll();
    }

    public synchronized void stopElevators() throws InterruptedException {
        while (flag) {
            wait();
            flag = false;
        }
    }

}
