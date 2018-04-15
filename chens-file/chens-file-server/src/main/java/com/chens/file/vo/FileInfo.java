package com.chens.file.vo;

/**
 * 待上传文件信息
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/13
 */
public class FileInfo {
    private String md5;
    private String guid;
    private String chunk;
    private String chunks;
    private String uploadFolderPath;
    private String fileName;
    private String ext;

    public FileInfo(String md5, String guid, String chunk, String chunks, String uploadFolderPath, String fileName, String ext) {
        this.md5 = md5;
        this.guid = guid;
        this.chunk = chunk;
        this.chunks = chunks;
        this.uploadFolderPath = uploadFolderPath;
        this.fileName = fileName;
        this.ext = ext;
    }


    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getChunk() {
        return chunk;
    }

    public void setChunk(String chunk) {
        this.chunk = chunk;
    }

    public String getChunks() {
        return chunks;
    }

    public void setChunks(String chunks) {
        this.chunks = chunks;
    }

    public String getUploadFolderPath() {
        return uploadFolderPath;
    }

    public void setUploadFolderPath(String uploadFolderPath) {
        this.uploadFolderPath = uploadFolderPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
