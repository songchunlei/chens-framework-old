package com.chens.file.service.impl;

import com.chens.file.service.IFilePathGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 按日期生成路径
 *
 * @author songchunlei@qq.com
 * @create 2018/4/17
 */
public class DatePathGenerator implements IFilePathGenerator {

    private String parsePattern = "yyyy/MM/dd";
    private SimpleDateFormat sdf = new SimpleDateFormat(parsePattern);

    @Override
    public String getPath() {
        return sdf.format(new Date());
    }
}
