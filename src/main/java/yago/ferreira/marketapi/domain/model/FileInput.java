package yago.ferreira.marketapi.domain.model;

import java.io.FileInputStream;

public class FileInput {
    private String name;
    private String originalFilename;
    private String contentType;
    private FileInputStream fileInputStream;
    private byte[] bytes;

    public FileInput() {
    }

    public FileInput(String name, String originalFilename, String contentType, FileInputStream fileInputStream, byte[] bytes) {
        this.name = name;
        this.originalFilename = originalFilename;
        this.contentType = contentType;
        this.fileInputStream = fileInputStream;
        this.bytes = bytes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public FileInputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(FileInputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
