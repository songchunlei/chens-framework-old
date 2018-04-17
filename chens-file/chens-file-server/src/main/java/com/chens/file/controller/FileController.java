package com.chens.file.controller;

import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.util.UUIDUtil;
import com.chens.core.vo.Result;
import com.chens.file.exception.FileException;
import com.chens.file.exception.FileExceptionEnum;
import com.chens.file.service.IFileInfoService;
import com.chens.file.entity.SysFile;
import com.chens.file.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.chens.file.util.FileUtil.createMd5;

/**
 * 通用文件服务
 *
 * @author songchunlei@qq.com
 * @create 2018/3/13
 */
@Controller
@RequestMapping("/fileController")
public class FileController extends BaseFileController {

    @Autowired
    private IFileInfoService fileInfoService;

    @PostMapping(value = "/upload")
    public ResponseEntity<Result> fileUpload(@RequestParam("file") MultipartFile file) {
        SysFile sysFile;
        String name = file.getOriginalFilename();
        String fileName = file.getName();
        long size = file.getSize();
        String type = FileUtil.getExtention(name);
        try {
            saveFile(getFilePath(), name+"."+type, file);
        } catch (Exception ex) {
            throw new FileException(FileExceptionEnum.FILE_READING_ERROR.getCode(),ex.getMessage());
        }
        try {
            sysFile = new SysFile(name, UUIDUtil.getUUID(), getFilePath(), createMd5(file).toString(), null, size);
            fileInfoService.insert(sysFile);
        } catch (Exception e) {
            throw new FileException(FileExceptionEnum.FILE_SAVE_ERROR);
        }

        return doSuccess(sysFile);
    }



}
