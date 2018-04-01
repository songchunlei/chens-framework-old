package com.chens.file.controller;

import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseWebController;
import com.chens.file.service.IFileInfoService;
import com.chens.file.entity.SysFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;
import static com.chens.file.util.FileUtil.createMd5;

/**
 * 文件服务
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/13
 */
@Controller
@RequestMapping("/fileController")
public class FileController extends BaseFileController {


    @PostMapping(value = "/upload")
    public ResponseEntity<Result> fileUpload(@RequestParam("name") String name,
                                             @RequestParam("type") String type,
                                             @RequestParam("size") long size,
                                             @RequestParam("file") MultipartFile file) {
        String fileName;
        SysFile sysFile;

        try {
            saveFile(getFilePath(), name+"."+type, file);
        } catch (Exception ex) {
            throw new BaseException(BaseExceptionEnum.FILE_READING_ERROR.getCode(),ex.getMessage());
        }
        try {
            sysFile = new SysFile(name+type,size,createMd5(file).toString());
            service.insert(sysFile);
        } catch (Exception e) {
            throw new BaseException(BaseExceptionEnum.FILE_SAVE_ERROR);
        }

        return doSuccess(sysFile);
    }



}
