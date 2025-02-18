package yago.ferreira.marketapi.application.service.file;

import org.springframework.stereotype.Component;
import yago.ferreira.marketapi.adapters.in.controller.dto.response.FileResponse;
import yago.ferreira.marketapi.domain.model.FileInput;
import yago.ferreira.marketapi.domain.port.in.usecases.FileUseCases;

@Component
public class FileServiceImpl implements FileUseCases {

    @Override
    public FileResponse storeFile(FileInput file) {
        return null;
    }

    @Override
    public FileResponse storeAvatar(FileInput file) {
        return null;
    }

}
