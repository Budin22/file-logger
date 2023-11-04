package org.example.config;

import org.example.exception.ConfigurationLoaderException;

public interface FileLoggerConfigurationLoader {
    FileLoggerConfiguration load(String path) throws ConfigurationLoaderException;
}
