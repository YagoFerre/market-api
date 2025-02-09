package yago.ferreira.marketapi.application.service.file;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.infra.config.storage.FileStorageProperties;
import yago.ferreira.marketapi.adapters.in.controller.response.FileResponse;

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

    public FileResponse storeFile(MultipartFile file) {
        return storeFileInternal(file, storageLocation);
    }

    public FileResponse storeAvatar(MultipartFile file) {
        return storeFileInternal(file, avatarStorageLocation);
    }

    private FileResponse storeFileInternal(MultipartFile file, Path targetLocation) {
        try {
            String fileName = generateUniqueFileName(file);
            Path targetPath = targetLocation.resolve(fileName);

            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);


            return new FileResponse(fileName, targetLocation.toString());
        } catch (IOException ex) {
            throw new RuntimeException("Falha ao armazenar arquivo " + file.getOriginalFilename(), ex);
        }
    }

    private String generateUniqueFileName(MultipartFile file) {
        String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()).replace(' ', '_'));
        Long timestamp = System.currentTimeMillis();
        return timestamp + "_" + originalFileName;
    }

}
