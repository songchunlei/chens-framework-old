package com.chens.file.service.impl;

import com.chens.file.entity.SysFile;
import com.chens.file.exception.FileException;
import com.chens.file.exception.FileExceptionEnum;
import com.chens.file.service.IFileInfoService;
import com.chens.file.service.IFilePathGenerator;
import com.chens.file.service.IFileService;
import com.chens.file.util.FileUtil;
import com.chens.file.vo.FileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * 文件服务
 *
 * @author songchunlei@qq.com
 * @create 2018/4/17
 */
@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    private IFileInfoService fileInfoService;

    private String ROOT_DIR = "/opt/upload";

    private IFilePathGenerator filePathGenerator = new DatePathGenerator();

    @Override
    public SysFile upload(FileData fileData) {
        SysFile sysFile;
        try (ByteArrayInputStream in = new ByteArrayInputStream(fileData.getData())) {
            return this.saveFile(in, fileData);
        } catch (IOException e) {
            throw new FileException(FileExceptionEnum.FILE_SAVE_ERROR.getCode(),e.getMessage());
        }
    }

    @Override
    public byte[] download(String name) {
        return new byte[0];
    }

    @Override
    public boolean deleteByName(String name) {
        return false;
    }

    /**
     * 保存文件，并保存文件记录
     * @param in
     * @param data
     * @return
     */
    private SysFile saveFile(InputStream in,FileData data) {
        SysFile sysFile = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            FileUtil.copy(in, out);
            out.flush();
            String realPath = this.saveByFileInfo(out.toByteArray(), data);
            sysFile = new SysFile(data,realPath);
            fileInfoService.insert(sysFile);
        } catch (Exception e) {
            throw new FileException(FileExceptionEnum.FILE_SAVE_ERROR.getCode(),e.getMessage());
        }
        return sysFile;
    }

    private String saveByFileInfo(byte[] data, FileData fileData) {
        try (InputStream in = new ByteArrayInputStream(data)) {
            return this.saveByFileInfo(in, fileData);
        } catch (IOException e) {
            throw new FileException(FileExceptionEnum.FILE_SAVE_ERROR.getCode(),e.getMessage());
        }
    }


    private String saveByFileInfo(InputStream in, FileData fileData) {
        String dir = ROOT_DIR + "/" + filePathGenerator.getPath();
        String filePath = dir + "/" + fileData.getName() + fileData.getType();
        FileUtil.mkDirs(filePath);
        try (FileOutputStream fout = new FileOutputStream(filePath)) {
            FileUtil.copy(in, fout);
        } catch (IOException e) {
            throw new FileException(FileExceptionEnum.FILE_SAVE_ERROR.getCode(),e.getMessage());
        }
        return filePath;
    }



}
