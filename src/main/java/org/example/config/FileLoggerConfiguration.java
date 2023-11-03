package org.example.config;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileLoggerConfiguration {
    private String pathToFile = "src/main/resources/";
    private String fileName = String.format("log_%s.txt", new SimpleDateFormat("dd.M.yyyy_hh.mm.ss.SSS").format(new Date()));
    private boolean extend = false;
    private LoginLevel level = LoginLevel.INFO;
    private long maxSize = 512;
    private String format = "[%tc][%s] Message: %s %n";

    public String getPath() {
        return pathToFile + fileName;
    }

    public boolean isExtend() {
        return extend;
    }

    public void setExtend(boolean extend) {
        this.extend = extend;
    }

    public LoginLevel getLevel() {
        return level;
    }

    public long getMaxSize() {
        return maxSize;
    }

    public String getFormat() {
        return format;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public void setLevel(LoginLevel level) {
        this.level = level;
    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return "FileLoggerConfiguration{" +
                "pathToFile='" + pathToFile + '\'' +
                ", fileName='" + fileName + '\'' +
                ", extend=" + extend +
                ", level=" + level +
                ", maxSize=" + maxSize +
                ", format='" + format + '\'' +
                '}';
    }
}
