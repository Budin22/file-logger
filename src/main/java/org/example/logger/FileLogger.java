package org.example.logger;

import org.example.config.FileLoggerConfiguration;
import org.example.config.FileLoggerConfigurationImp;
import org.example.config.LoggingLevel;
import org.example.exception.FileMaxSizeReachedException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileLogger implements Logger {
    private final FileLoggerConfiguration config;
    private File fileWithLogs;
    public FileLogger() {
        this.config = new FileLoggerConfigurationImp.Builder().build();
    }
    public FileLogger(FileLoggerConfiguration config) {
        this.config = config;
    }

    private boolean checkFileSizeLimitation(File file, long maxSize) {
        return file.length() >= maxSize;
    }

    private File getNewFileForLogs(){
        String fileName = String.format("log_%s.txt", new SimpleDateFormat("dd.M.yyyy_hh.mm.ss.SSS").format(new Date()));
        return new File(config.getPath() + fileName);
    }

    private void writeMessage(String message, LoggingLevel level) throws FileMaxSizeReachedException {
        if(getFileWithLogs() == null){
            setFileWithLogs(getNewFileForLogs());
        }

        if(checkFileSizeLimitation(getFileWithLogs(), config.getMaxSize())){
            if(config.isExtend()){
                setFileWithLogs(getNewFileForLogs());
            } else {
                throw new FileMaxSizeReachedException(String.format("File size: %d bytes, but maxSize: %d bytes, file: %s", fileWithLogs.length(), config.getMaxSize(), fileWithLogs.getAbsolutePath()));
            }
        }

        try (FileWriter file = new FileWriter(getFileWithLogs(), true);
             Writer writer = new BufferedWriter(file)
        ) {
            writer.append(String.format(config.getFormat(), new Date(), level, message));
            writer.flush();
        } catch (IOException e) {
            System.out.println("Get IOException: " + e.getMessage());
        }
    }

    @Override
    public void debug(String message) {
        if(config.getLevel().name().equals("DEBUG")){
            try {
                writeMessage(message, LoggingLevel.DEBUG);
            } catch (FileMaxSizeReachedException e) {
                System.out.println("Get FileMaxSizeReachedException with debug method: " + e.getMessage());
            }
        }
    }

    @Override
    public void info(String message) {
        try {
            writeMessage(message, LoggingLevel.INFO);
        } catch (FileMaxSizeReachedException e) {
            System.out.println("Get FileMaxSizeReachedException with info method: " + e.getMessage());
        }
    }

    public File getFileWithLogs() {
        return fileWithLogs;
    }

    public void setFileWithLogs(File fileWithLogs) {
        this.fileWithLogs = fileWithLogs;
    }
}
