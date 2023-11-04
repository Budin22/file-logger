package org.example.config;

public interface FileLoggerConfiguration {
    String getPath();

    boolean isExtend();

    LoginLevel getLevel();

    long getMaxSize();

    String getFormat();
}
