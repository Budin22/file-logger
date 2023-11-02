package org.example.logger;

import org.example.config.LoginLevel;
import org.example.exception.FileMaxSizeReachedException;

import java.io.*;
import java.util.Date;

public class FileLogger {
    private static String filePath = "src/main/resources/logs/text.txt";
    private static long maxSize = 1;

    private static void checkFileSizeLimitation() throws FileMaxSizeReachedException {
        File myFile = new File(filePath);
        long fileSize = myFile.length();
        if(fileSize >= maxSize){
            throw new FileMaxSizeReachedException(String.format("File size: %d bytes, but maxSize: %d bytes, file: %s", fileSize, maxSize, filePath));
        }
    }

    private static void writeMessage(String message, String level){
        try(FileWriter file = new FileWriter(filePath, true);
            BufferedWriter writer = new BufferedWriter(file)
        ) {
            checkFileSizeLimitation();
            writer.append(String.format("[%tc][%s] Message: %s %n", new Date(), level, message));
            writer.flush();
        } catch (IOException e) {
            System.out.println("Get IOException: " + e.getMessage());
        } catch (FileMaxSizeReachedException e) {
            System.out.println("Get FileMaxSizeReachedException: " + e.getMessage());
        }
    }
    public static void debug(String message) {
        writeMessage(message, LoginLevel.DEBUG.name());
    }

    public static void info(String message) {
        writeMessage(message, LoginLevel.INFO.name());
    }
}
