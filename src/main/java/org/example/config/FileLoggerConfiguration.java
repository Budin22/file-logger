package org.example.config;

public interface FileLoggerConfiguration {
    String getPath();

    boolean isExtend();

    LoggingLevel getLevel();

    long getMaxSize();

    String getFormat();
}
