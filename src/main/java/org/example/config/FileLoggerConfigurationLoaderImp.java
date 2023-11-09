package org.example.config;

import org.example.exception.ConfigurationLoaderException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileLoggerConfigurationLoaderImp implements FileLoggerConfigurationLoader {
    public FileLoggerConfiguration load(String path) throws ConfigurationLoaderException {
        try (FileReader fileReader = new FileReader(path);
             BufferedReader reader = new BufferedReader(fileReader)) {

            Map<String, String> dataFromFile = new HashMap<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] res = line.split(" : ");
                dataFromFile.put(res[0].trim(), res[1].trim());
            }

            return new FileLoggerConfigurationImp.Builder()
                    .setPath(dataFromFile.get("FILE"))
                    .setExtend(Boolean.parseBoolean(dataFromFile.get("EXTEND")))
                    .setMaxSize(Long.parseLong(dataFromFile.get("MAX-SIZE")))
                    .setLevel(LoggingLevel.valueOf(dataFromFile.get("LEVEL")))
                    .setFormat(dataFromFile.get("FORMAT"))
                    .build();
        } catch (FileNotFoundException e) {
            throw new ConfigurationLoaderException("Get FileNotFoundException in FileLoggerConfigurationLoader: " + e.getMessage());
        } catch (IOException e) {
            throw new ConfigurationLoaderException("Get IOException in FileLoggerConfigurationLoader: " + e.getMessage());
        }
    }
}
