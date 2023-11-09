package org.example;

import org.example.config.FileLoggerConfiguration;
import org.example.config.FileLoggerConfigurationImp;
import org.example.config.FileLoggerConfigurationLoader;
import org.example.config.FileLoggerConfigurationLoaderImp;
import org.example.exception.ConfigurationLoaderException;
import org.example.logger.FileLogger;
import org.example.logger.Logger;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            FileLoggerConfigurationLoader configLoader = new FileLoggerConfigurationLoaderImp();

            FileLoggerConfiguration config = configLoader.load("src/main/resources/config.txt");
            FileLoggerConfiguration defConfig = new FileLoggerConfigurationImp.Builder().setPath("src/main/resources/").build();

            Logger logger = new FileLogger(config);

            logger.info("info write some");
            logger.debug("info write some");
            logger.info("info write some");
            logger.debug("info write some");
            logger.info("info write some");
            logger.debug("info write some");
            logger.info("info write some");
            logger.debug("info write some");
            logger.info("info write some");
            logger.debug("info write some");
        } catch (ConfigurationLoaderException e) {
            System.out.println(e.getMessage());
        }
    }
}