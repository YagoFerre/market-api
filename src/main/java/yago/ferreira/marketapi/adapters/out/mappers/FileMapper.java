package yago.ferreira.marketapi.adapters.out.mappers;

import org.mapstruct.Mapper;
import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.domain.model.FileInput;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FileMapper {
    MultipartFile toMultipartFile(FileInput fileInput);
    FileInput toFileInputDomain(MultipartFile multipartFile);
    List<MultipartFile> toMultipartFileList(List<FileInput> fileInputList);
    List<FileInput> toFileInputDomainList(List<MultipartFile> multipartFileList);
}
