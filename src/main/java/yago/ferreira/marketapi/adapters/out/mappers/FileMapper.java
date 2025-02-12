package yago.ferreira.marketapi.adapters.out.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.domain.model.FileInput;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FileMapper {
    FileMapper INSTANCE = Mappers.getMapper(FileMapper.class);

    MultipartFile toMultipartFile(FileInput fileInput);
    FileInput toFileInputDomain(MultipartFile multipartFile);
    List<MultipartFile> toMultipartFileList(List<FileInput> fileInputList);
    List<FileInput> toFileInputDomainList(List<MultipartFile> multipartFileList);
}
