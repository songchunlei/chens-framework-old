package com.chens.file.controller;

import com.chens.core.web.BaseWebController;
import com.chens.file.entity.SysFile;
import com.chens.file.service.IFileInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 文件信息查询
 *
 * @author songchunlei@qq.com
 * @create 2018/4/14
 */
@Controller
@RequestMapping("/fileInfoController")
public class FileInfoController extends BaseWebController<IFileInfoService,SysFile> {
}
