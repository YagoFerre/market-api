package yago.ferreira.marketapi.entity.mapper;

import org.springframework.stereotype.Component;
import yago.ferreira.marketapi.entity.File;
import yago.ferreira.marketapi.entity.dto.FileDTO;

@Component
public class FileMapper {

    public FileDTO toDTO(File file) {
        return new FileDTO();
    }

    public File toEntity(FileDTO fileDTO) {
        return new File();
    }

}
