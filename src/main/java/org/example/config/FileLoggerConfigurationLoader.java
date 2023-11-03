package org.example.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileLoggerConfigurationLoader {
    public  static FileLoggerConfiguration load(String path) {

        try (FileReader fileReader = new FileReader(path);
             BufferedReader reader = new BufferedReader(fileReader)) {

            Map<String, String> dataFromFile = new HashMap<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] res = line.split(" : ");
                dataFromFile.put(res[0].trim(), res[1].trim());
            }

            FileLoggerConfiguration config = new FileLoggerConfiguration();
            config.setPathToFile(dataFromFile.get("FILE"));
            config.setLevel(LoginLevel.valueOf(dataFromFile.get("LEVEL")));
            config.setMaxSize(Long.parseLong(dataFromFile.get("MAX-SIZE")));
            config.setFormat(dataFromFile.get("FORMAT"));
            config.setExtend(Boolean.parseBoolean(dataFromFile.get("EXTEND")));

            return config;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
