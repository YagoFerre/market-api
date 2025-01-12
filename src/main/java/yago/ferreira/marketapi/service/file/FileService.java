package yago.ferreira.marketapi.service.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.config.storage.FileStorageProperties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {

    private final Path storageLocation;

    public FileService(FileStorageProperties fileStorageProperties) {
        this.storageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(storageLocation);
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível criar o diretório de upload.", e);
        }
    }

    public String storeFile(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            Path targetLocation = storageLocation.resolve(fileName);

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return targetLocation.toString();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar o arquivo: " + file.getOriginalFilename(), e);
        }
    }

}
