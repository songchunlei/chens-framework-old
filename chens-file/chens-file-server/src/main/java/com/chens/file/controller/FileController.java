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

/**
 * 文件服务
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/13
 */
@Controller
@RequestMapping("/FileUpload")
public class FileController extends BaseFileController{
    @Autowired
    private IFileInfoService fileInfoService;

    @RequestMapping(value = "/Index", method = RequestMethod.GET)
    public String Index() {
        return "FileUpload/Index";
    }

    @ResponseBody
    @RequestMapping(value = "/FileUp", method = RequestMethod.POST)
    public String fileUpload(@RequestParam("id") String id,
                             @RequestParam("name") String name,
                             @RequestParam("type") String type,
                             @RequestParam("lastModifiedDate") String lastModifiedDate,
                             @RequestParam("size") int size,
                             @RequestParam("file") MultipartFile file) {
        String fileName;

        try {
            String ext = name.substring(name.lastIndexOf("."));
            fileName = UUID.randomUUID().toString() + ext;
            saveFile(getRealPath(), fileName, file);
        } catch (Exception ex) {
            return "{\"error\":true}";
        }
        try {
            fileInfoService.insert(new SysFile(fileName,createMd5(file).toString(), new Date()));
        } catch (Exception e) {
            return "{\"error\":true}";
        }

        return "{jsonrpc = \"2.0\",id = id,filePath = \"/Upload/\" + fileFullName}";
    }
}
