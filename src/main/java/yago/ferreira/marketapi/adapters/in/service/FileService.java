package yago.ferreira.marketapi.adapters.in.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.adapters.in.controller.dto.response.FileResponse;
import yago.ferreira.marketapi.adapters.out.mappers.FileMapper;
import yago.ferreira.marketapi.domain.port.in.usecases.FileUseCases;

@Component
public class FileService {

    private final FileUseCases fileUseCases;
    private final FileMapper fileMapper;

    public FileService(FileUseCases fileUseCases, FileMapper fileMapper) {
        this.fileUseCases = fileUseCases;
        this.fileMapper = fileMapper;
    }

    public FileResponse storeFile(MultipartFile file) {
        return fileUseCases.executeStoreFile(fileMapper.toFileInputDomain(file));
    }

    public FileResponse storeAvatar(MultipartFile file) {
        return fileUseCases.executeStoreAvatar(fileMapper.toFileInputDomain(file));
    }

}
