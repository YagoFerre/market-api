package yago.ferreira.marketapi.adapters.in.controller.mappers;

import org.mapstruct.Mapper;
import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.domain.model.FileInput;

@Mapper(componentModel = "spring")
public interface FileMapper {
    MultipartFile toMultipartFile(FileInput objModel);
}
