package org.example.logger;

import org.example.config.FileLoggerConfiguration;
import org.example.config.FileLoggerConfigurationLoader;
import org.example.config.LoginLevel;
import org.example.exception.FileMaxSizeReachedException;

import java.io.*;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileLogger {
    private static FileLoggerConfiguration config = new FileLoggerConfiguration();

    public static void setConfig(FileLoggerConfiguration config) {
        FileLogger.config = config;
    }

    public static void loadConfigFromFile(String path) {
        FileLogger.config = FileLoggerConfigurationLoader.load(path);
    }

    private static void checkFileSizeLimitation() throws FileMaxSizeReachedException {

        File myFile = new File(config.getPath());
        long fileSize = myFile.length();
        if (fileSize >= config.getMaxSize()) {
            if (!config.isExtend()) {
                throw new FileMaxSizeReachedException(String.format("File size: %d bytes, but maxSize: %d bytes, file: %s", fileSize, config.getMaxSize(), config.getPath()));
            } else {
                config.setFileName(String.format("log_%s.txt", new SimpleDateFormat("dd.M.yyyy_hh.mm.ss.SSS").format(new Date())));
            }
        }
    }

    private static void writeMessage(String message, LoginLevel level) {


        try (FileWriter file = new FileWriter(config.getPath(), true);
             Writer writer = new BufferedWriter(file)
        ) {
            writer.append(String.format(config.getFormat(), new Date(), level, message));
            writer.flush();
        } catch (IOException e) {
            System.out.println("Get IOException: " + e.getMessage());
        }
    }

    public static void debug(String message) {
        try {
            checkFileSizeLimitation();
            if (config.getLevel().name().equals("DEBUG")) {
                writeMessage(message, LoginLevel.DEBUG);
            }
        } catch (FileMaxSizeReachedException e) {
            System.out.println("Get FileMaxSizeReachedException: " + e.getMessage());
        }
    }

    public static void info(String message) {
        try {
            checkFileSizeLimitation();
            writeMessage(message, LoginLevel.INFO);
        } catch (FileMaxSizeReachedException e) {
            System.out.println("Get FileMaxSizeReachedException: " + e.getMessage());
        }

    }
}
