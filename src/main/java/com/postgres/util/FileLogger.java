package com.postgres.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger {

    private static final String LOG_FILE_PATH = "logs/test-log.txt";
    private static final String PERSON_OPERATION_LOG_FILE_PATH = "logs/operations-log.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void logTestResult(String message) {
        log(LOG_FILE_PATH, message);
    }

    public static void logOperation(String message) {
        log(PERSON_OPERATION_LOG_FILE_PATH, message);
    }

    private static void log(String filePath, String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(LocalDateTime.now().format(FORMATTER) + " - " + message);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();


        }
    }
}
