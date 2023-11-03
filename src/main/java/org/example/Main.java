package org.example;

import org.example.config.FileLoggerConfiguration;
import org.example.config.LoginLevel;
import org.example.logger.FileLogger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        FileLoggerConfiguration config = new FileLoggerConfiguration();
        config.setLevel(LoginLevel.DEBUG);
        config.setMaxSize(300);
        config.setFormat("[%tc][%s] Some nice message: %s %n");
        config.setPathToFile("src/main/resources/");
        config.setExtend(true);
        FileLogger.setConfig(config);

        FileLogger.loadConfigFromFile("src/main/resources/config.txt");

        FileLogger.debug("message from debug");
        FileLogger.info("message from info");
        FileLogger.debug("message from debug");
        FileLogger.info("message from info");
        FileLogger.debug("message from debug");
        FileLogger.info("message from info");
        FileLogger.debug("message from debug");
        FileLogger.info("message from info");
        FileLogger.debug("message from debug");
        FileLogger.info("message from info");
        FileLogger.debug("message from debug");
        FileLogger.info("message from info");
        FileLogger.debug("message from debug");
        FileLogger.info("message from info");
    }
}