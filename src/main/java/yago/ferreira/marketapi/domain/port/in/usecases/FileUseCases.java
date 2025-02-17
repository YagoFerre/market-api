package yago.ferreira.marketapi.domain.port.in.usecases;

import yago.ferreira.marketapi.adapters.in.controller.dto.response.FileResponse;
import yago.ferreira.marketapi.domain.model.FileInput;

public interface FileUseCases {
    FileResponse storeFile(FileInput file);
}
