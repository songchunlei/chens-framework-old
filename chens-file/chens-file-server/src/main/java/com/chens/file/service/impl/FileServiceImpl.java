package com.chens.file.service.impl;

import com.chens.core.context.BaseContextHandler;
import com.chens.core.util.StringUtils;
import com.chens.file.entity.SysFile;
import com.chens.file.exception.FileException;
import com.chens.file.exception.FileExceptionEnum;
import com.chens.file.service.IFileInfoService;
import com.chens.file.service.IFilePathGenerator;
import com.chens.file.service.IFileService;
import com.chens.file.util.FileUtil;
import com.chens.file.vo.FileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 默认保存地址
     */
    private String ROOT_DIR = "/opt/upload";

    private IFilePathGenerator filePathGenerator = new DatePathGenerator();

    @Override
    public SysFile upload(FileData fileData) {
        try (ByteArrayInputStream in = new ByteArrayInputStream(fileData.getData())) {
            return this.saveFile(in, fileData);
        } catch (IOException e) {
            throw new FileException(FileExceptionEnum.FILE_SAVE_ERROR.getCode(),e.getMessage());
        }
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

    @Override
    public FileData download(String name) {
        FileData fileData;
        SysFile sysFile = fileInfoService.loadByName(name);

        if (sysFile == null) {
            throw new FileException(FileExceptionEnum.FILE_IS_NOT_FOUND);
        }
        else
        {
            fileData = new FileData(sysFile,this.loadDataByFileInfo(sysFile.getUrl()));
        }
        return fileData;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByName(String name) {
        SysFile sysFile = fileInfoService.loadByName(name);
        if(sysFile!=null)
        {
            //删除文件信息
            fileInfoService.deleteById(sysFile.getId());
            // 删除文件
            File file = new File(sysFile.getUrl());
            file.delete();
        }
        else
        {
            throw new FileException(FileExceptionEnum.FILE_IS_NOT_FOUND);
        }

        return false;
    }

    /**
     * 根据文件信息保存文件
     * @param data
     * @param fileData
     * @return
     */
    private String saveByFileInfo(byte[] data, FileData fileData) {
        try (InputStream in = new ByteArrayInputStream(data)) {
            return this.saveByFileInfo(in, fileData);
        } catch (IOException e) {
            throw new FileException(FileExceptionEnum.FILE_SAVE_ERROR.getCode(),e.getMessage());
        }
    }

    /**
     * 根据文件信息保存文件
     * @param in
     * @param fileData
     * @return
     */
    private String saveByFileInfo(InputStream in, FileData fileData) {
        //确定保存位置，默认为随机时间位置
        String dir = ROOT_DIR+ "/" + filePathGenerator.getPath();
        //如果存在指定保存位置，则保存指定位置
        if(StringUtils.isNotEmpty(fileData.getSavePath()))
        {
            dir = fileData.getSavePath();
        }
        //如果有租户存在，增加租户id模块，方便查询
        if(StringUtils.isNotEmpty(BaseContextHandler.getTenantId()))
        {
            dir = dir+"/"+ BaseContextHandler.getTenantId();
        }
        String filePath = dir + "/" + fileData.getName() + fileData.getType();
        FileUtil.mkDirs(filePath);
        try (FileOutputStream fout = new FileOutputStream(filePath)) {
            FileUtil.copy(in, fout);
        } catch (IOException e) {
            throw new FileException(FileExceptionEnum.FILE_SAVE_ERROR.getCode(),e.getMessage());
        }
        return filePath;
    }

    /**
     * 加载文件
     * @param url
     * @return
     */
    private byte[] loadDataByFileInfo(String url) {
        try (InputStream in = loadByFileInfo(url);
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            FileUtil.copy(in, out);
            out.flush();
            return out.toByteArray();
        } catch (IOException e) {
            throw new FileException(FileExceptionEnum.FILE_READING_ERROR);
        }
    }

    /**
     * 加载文件
     * @param url
     * @return
     */
    private InputStream loadByFileInfo(String url) {
        try {
            return new FileInputStream(url);
        } catch (FileNotFoundException e) {
            throw new FileException(FileExceptionEnum.FILE_READING_ERROR);
        }
    }


}
