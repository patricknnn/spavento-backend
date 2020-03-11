package com.spavento.paintings.models;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class DBImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long paintingId;
    @Column
    private String fileName;
    @Column
    private String fileType;
    @Lob
    private byte[] data;

    public DBImage() {
    }

    public DBImage(Long paintingId, String fileName, String fileType, byte[] data) {
        this.paintingId = paintingId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPaintingId() {
        return paintingId;
    }

    public void setPaintingId(Long paintingId) {
        this.paintingId = paintingId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
