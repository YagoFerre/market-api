package yago.ferreira.marketapi.domain.port.in.usecases;

import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.adapters.in.controller.dto.response.FileResponse;

public interface FileUseCases {
    FileResponse storeFile(MultipartFile file);

    FileResponse storeAvatar(MultipartFile file);
}
