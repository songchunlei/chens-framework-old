package com.chens.file.web;

import com.chens.core.constants.CommonConstants;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseController;
import com.chens.file.constants.FileConstants;
import com.chens.file.exception.FileException;
import com.chens.file.exception.FileExceptionEnum;
import com.chens.file.service.IFileService;
import com.chens.file.util.FileUtil;
import com.chens.file.vo.FileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * 通用文件服务
 *
 * @author songchunlei@qq.com
 * @create 2018/3/13
 */
public abstract class BaseFileController extends BaseController {

    @Autowired
    protected IFileService fileService;

    @PostMapping(value = "/upload")
    public ResponseEntity<Result> fileUpload(@RequestParam("file") MultipartFile file,String groupId) {
        FileData fileData = FileUtil.getFileData(file,groupId);
        return doSuccess(FileConstants.UPLOAD_SUCCESS,fileService.upload(fileData));
    }


    /**
     * @Description: 文件下载
     * @param name 文件名
     */
    @RequestMapping("/download")
    public void downloadFile(String name, HttpServletRequest request, HttpServletResponse response) {
        FileData fileData = fileService.download(name);
        if(fileData==null)
        {
            throw new FileException(FileExceptionEnum.FILE_IS_NOT_FOUND);
        }
        try (InputStream in = new ByteArrayInputStream(fileData.getData()); OutputStream out = response.getOutputStream();) {
            response.setContentType(fileData.getType());
            /*"UTF-8"*/
            response.setCharacterEncoding(CommonConstants.CHARACTER_UTF8);
            // 解决不同浏览器中文文件名乱码问题
            if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
                // firefox浏览器
                response.setHeader("Content-disposition", "attachment; filename=" + new String(fileData.getOrgName().getBytes(CommonConstants.CHARACTER_UTF8), CommonConstants.CHARACTER_ISO88591));
            } else {
                response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileData.getOrgName(), CommonConstants.CHARACTER_UTF8));
            }
            FileUtil.copy(in, out);
            out.flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } catch (FileException e) {
            logger.error(e.getMessage(), e);
        }
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Result> deleteFile(String name) {
        return doSuccess(FileConstants.DELETE_SUCCESS,fileService.deleteByName(name));

    }

}
