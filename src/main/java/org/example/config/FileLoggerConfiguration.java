package org.example.config;

public class FileLoggerConfiguration {
    private final String path;
    private final boolean extend;
    private final LoginLevel level;
    private final long maxSize;
    private final String format;

    private FileLoggerConfiguration(Builder builder) {
        this.path = builder.path;
        this.extend = builder.extend;
        this.level = builder.level;
        this.maxSize = builder.maxSize;
        this.format = builder.format;

    }

    public static class Builder {
        private String path = "src/main/resources/";
        private boolean extend = false;
        private LoginLevel level = LoginLevel.INFO;
        private long maxSize = 512;
        private String format = "[%tc][%s] Message: %s %n";

        public FileLoggerConfiguration build() {

            return new FileLoggerConfiguration(this);
        }

        public Builder setPath(String pathToFile) {
            this.path = pathToFile;
            return this;
        }

        public Builder setLevel(LoginLevel level) {
            this.level = level;
            return this;
        }

        public Builder setMaxSize(long maxSize) {
            this.maxSize = maxSize;
            return this;
        }

        public Builder setExtend(boolean extend) {
            this.extend = extend;
            return this;
        }

        public Builder setFormat(String format) {
            this.format = format;
            return this;
        }
    }

    public String getPath() {
        return path;
    }

    public boolean isExtend() {
        return extend;
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
}
