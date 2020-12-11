package by.elevator.service;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Slf4j
public class StatisticService {

    private static final String FILE_NAME = "statistic.txt";

    public static void createFile() {
        try {
            File file = new File(FILE_NAME);
            if (file.createNewFile()) {
                log.info("***Statistic file {} created***", FILE_NAME);
            }
            clearFile();
        } catch (IOException e) {
            log.error(e.toString());
        }
    }

    public static void addNote(String identificationCode, String information) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.append("Elevator: ").append(identificationCode).append(". ").append(information);
            writer.append("\n");
            writer.flush();
        } catch (IOException e) {
            log.error(e.toString());
        }
    }

    private static void clearFile() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.append("");
        } catch (IOException e) {
            log.error(e.toString());
        }
    }

    public static void addNote(String identificationCode, int information) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.append("Elevator: ").append(identificationCode).append(". Total passengers: ").append(Integer.toString(information));
            writer.append("\n");
            writer.flush();
        } catch (IOException e) {
            log.error(e.toString());
        }
    }
}
