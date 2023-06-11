package com.rudkids.rudkids.infrastructure.image;

import com.rudkids.rudkids.domain.image.domain.FileExtension;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FileNameGenerator {

    public String generate(String originFileName) {
        String fileName = createNewName();
        String extension = getExtension(originFileName);
        return fileName + "." + extension;
    }

    private String createNewName() {
        return UUID.randomUUID().toString();
    }

    private String getExtension(String originFileName) {
        String extension = FilenameUtils.getExtension(originFileName);
        return FileExtension.getSupportedExtension(extension);
    }
}
