package com.chens.bpm.demo.sync;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 命令
 *
 * @author songchunlei@qq.com
 * @create 2018/1/7
 */
public class MyShell {
    public static void main(String[] args) throws IOException {
        List<String> cmdList = new ArrayList<>(8);
        //java原生命令执行测试
        cmdList.add("ls");
        cmdList.add("-s");
        ProcessBuilder processBuilder = new ProcessBuilder(cmdList);
        Process process = processBuilder.start();
        String result = convertStreamToStr(process.getInputStream());

        System.out.println(result);
    }

    //读取输出流并转换为字符串
    public static String convertStreamToStr(InputStream is) throws IOException {
        if (is != null) {
            Writer writer = new StringWriter();
            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is,
                        "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }
            return writer.toString();
        } else {
            return "";
        }
    }
}
