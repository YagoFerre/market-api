package yago.ferreira.marketapi.config.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;
    private String uploadAvatarDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public String getUploadAvatarDir() {
        return uploadAvatarDir;
    }

    public void setUploadAvatarDir(String uploadAvatarDir) {
        this.uploadAvatarDir = uploadAvatarDir;
    }
}
