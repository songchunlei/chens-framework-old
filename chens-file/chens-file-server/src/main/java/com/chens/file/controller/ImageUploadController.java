package com.chens.file.controller;

import com.chens.file.service.IFileInfoService;
import com.chens.file.entity.SysFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

import static com.chens.file.util.FileUtil.createMd5;
import static com.chens.file.util.FileUtil.deepClone;
import static com.chens.file.util.FileUtil.isImage;

@Controller
@RequestMapping("/ImageUpload")
public class ImageUploadController extends BaseFileController{
    @Autowired
    private IFileInfoService fileInfoService;

    @RequestMapping(value = "/Index", method = RequestMethod.GET)
    public String Upload() {
        return "ImageUpload/Upload";
    }

    @ResponseBody
    @RequestMapping(value = "/ImageUp", method = RequestMethod.POST)
    public String fileUpload(@RequestParam("id") String id,
                             @RequestParam("name") String name,
                             @RequestParam("type") String type,
                             @RequestParam("lastModifiedDate") String lastModifiedDate,
                             @RequestParam("size") long size,
                             @RequestParam("file") MultipartFile file) {
        String fileName = "";

        MultipartFile saveFile = null;

        try {
            saveFile = (MultipartFile) deepClone(file);
            java.io.File tempFile = new java.io.File(UUID.randomUUID().toString());
            file.transferTo(tempFile);
            if (!isImage(tempFile))
                return "{\"error\":true}";

            String ext = name.substring(name.lastIndexOf("."));
            fileName = UUID.randomUUID().toString() + ext;

            saveFile(getFilePath(), fileName, saveFile);

            fileInfoService.insert(new SysFile(fileName, size,createMd5(file).toString()));

        } catch (Exception ex) {
            return "{\"error\":true}";
        }

        return "{jsonrpc = \"2.0\",id = id,filePath = \"/Upload/\" + fileFullName}";
    }


}

