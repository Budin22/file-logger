package org.example.config;

import java.io.File;

public class FileLoggerConfigurationImp implements FileLoggerConfiguration {
    private String path = "src/main/resources/";
    private boolean extend = false;
    private LoggingLevel level = LoggingLevel.DEBUG;
    private long maxSize = 512;
    private String format = "[%tc][%s] Message: %s %n";

    public static class Builder {
        private final FileLoggerConfigurationImp config = new FileLoggerConfigurationImp();

        public FileLoggerConfiguration build() {
            return config;
        }

        public Builder setPath(String pathToFile) {
            config.path = pathToFile;
            return this;
        }

        public Builder setLevel(LoggingLevel level) {
            config.level = level;
            return this;
        }

        public Builder setMaxSize(long maxSize) {
            config.maxSize = maxSize;
            return this;
        }

        public Builder setExtend(boolean extend) {
            config.extend = extend;
            return this;
        }

        public Builder setFormat(String format) {
            config.format = format;
            return this;
        }
    }

    public String getPath() {
        return path;
    }

    public boolean isExtend() {
        return extend;
    }

    public LoggingLevel getLevel() {
        return level;
    }

    public long getMaxSize() {
        return maxSize;
    }

    public String getFormat() {
        return format;
    }
}
