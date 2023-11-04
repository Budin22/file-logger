package org.example.logger;

import org.example.config.FileLoggerConfiguration;
import org.example.config.FileLoggerConfigurationImp;
import org.example.config.LoginLevel;
import org.example.exception.FileMaxSizeReachedException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileLoggerImp implements FileLogger {
    private FileLoggerConfiguration config = new FileLoggerConfigurationImp.Builder().build();
    private File fileWithLogs;
    public FileLoggerImp() {
    }
    public FileLoggerImp(FileLoggerConfiguration config) {
        this.config = config;
    }

    private boolean checkFileSizeLimitation(File file, long maxSize) {
        return file.length() >= maxSize;
    }

    private File getNewFileForLogs(){
        String fileName = String.format("log_%s.txt", new SimpleDateFormat("dd.M.yyyy_hh.mm.ss.SSS").format(new Date()));
        return new File(config.getPath() + fileName);
    }

    private void writeMessage(String message, LoginLevel level) throws FileMaxSizeReachedException {
        if(getFileWithLogs() == null){
            setFileWithLogs(getNewFileForLogs());
        }

        if(checkFileSizeLimitation(getFileWithLogs(), config.getMaxSize())){
            if(config.isExtend()){
                setFileWithLogs(getNewFileForLogs());
            } else {
                throw new FileMaxSizeReachedException(String.format("File size: %d bytes, but maxSize: %d bytes, file: %s", fileWithLogs.length(), config.getMaxSize(), config.getPath()));
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
                writeMessage(message, LoginLevel.DEBUG);
            } catch (FileMaxSizeReachedException e) {
                System.out.println("Get FileMaxSizeReachedException with debug method: " + e.getMessage());
            }
        }
    }

    @Override
    public void info(String message) {
        try {
            writeMessage(message, LoginLevel.INFO);
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
