package yago.ferreira.marketapi.service.file;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.config.storage.FileStorageProperties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileService {

    private final Path storageLocation;
    private final Path avatarStorageLocation;

    public FileService(FileStorageProperties fileStorageProperties) {
        this.storageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        this.avatarStorageLocation = Paths.get(fileStorageProperties.getUploadAvatarDir()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(storageLocation);
            Files.createDirectories(avatarStorageLocation);
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível criar o diretório de upload.", e);
        }
    }

    public String storeFile(MultipartFile file) {
        return storeFileInternal(file, storageLocation);
    }

    public String storeAvatar(MultipartFile file) {
        return storeFileInternal(file, avatarStorageLocation);
    }

    private String storeFileInternal(MultipartFile file, Path targetLocation) {
        try {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(Objects.requireNonNull(file.getOriginalFilename()).replace(' ', '_')));
            Path targetPath = targetLocation.resolve(fileName);

            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            return targetLocation.toString();
        } catch (IOException ex) {
            throw new RuntimeException("Falha ao armazenar arquivo " + file.getOriginalFilename(), ex);
        }
    }

}
