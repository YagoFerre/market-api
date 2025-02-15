package yago.ferreira.marketapi.adapters.out.mappers;

import org.mapstruct.Mapper;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.domain.model.FileInput;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface FileMapper {
    List<FileInput> toFileInputDomainList(List<MultipartFile> multipartFileList);

    default FileInput toFileInputDomain(MultipartFile multipartFile) {
        if (multipartFile == null) {
            return null;
        }
        FileInput fileInput = new FileInput();
        fileInput.setName(multipartFile.getName());
        fileInput.setOriginalFilename(multipartFile.getOriginalFilename());
        fileInput.setContentType(multipartFile.getContentType());
        try {
            fileInput.setBytes(multipartFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Falha ao converter MultipartFile para FileInput", e);
        }
        return fileInput;
    }

    default MultipartFile toMultipartFile(FileInput fileInput) {
        return new MockMultipartFile(
                fileInput.getName(),
                fileInput.getOriginalFilename(),
                fileInput.getContentType(),
                fileInput.getBytes()
        );
    }

    default List<MultipartFile> toMultipartFileList(List<FileInput> fileInputList) {
        return fileInputList.stream().map(this::toMultipartFile).collect(Collectors.toList());
    }
}
