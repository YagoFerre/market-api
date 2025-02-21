package yago.ferreira.marketapi.application.service.file;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import yago.ferreira.marketapi.adapters.in.controller.dto.response.FileResponse;
import yago.ferreira.marketapi.domain.model.FileInput;
import yago.ferreira.marketapi.domain.port.in.usecases.FileUseCases;
import yago.ferreira.marketapi.infra.configuration.storage.FileStorageProperties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Component
public class FileServiceImpl implements FileUseCases {

    private final Path storageLocation;
    private final Path avatarStorageLocation;

    public FileServiceImpl(FileStorageProperties fileStorageProperties) {
        this.storageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        this.avatarStorageLocation = Paths.get(fileStorageProperties.getUploadAvatarDir()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(storageLocation);
            Files.createDirectories(avatarStorageLocation);
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível criar o diretório de upload.", e);
        }
    }

    @Override
    public FileResponse executeStoreFile(FileInput file) {
        return storeFileInternal(file, storageLocation);
    }

    @Override
    public FileResponse executeStoreAvatar(FileInput file) {
        return storeFileInternal(file, avatarStorageLocation);
    }

    private FileResponse storeFileInternal(FileInput file, Path targetLocation) {
        try {
            String fileName = generateUniqueFileName(file);
            Path targetPath = targetLocation.resolve(fileName);

            Files.copy(file.getFileInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);


            return new FileResponse(fileName, targetLocation.toString());
        } catch (IOException ex) {
            throw new RuntimeException("Falha ao armazenar arquivo " + file.getOriginalFilename(), ex);
        }
    }

    private String generateUniqueFileName(FileInput file) {
        String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()).replace(' ', '_'));
        Long timestamp = System.currentTimeMillis();
        return timestamp + "_" + originalFileName;
    }

}
