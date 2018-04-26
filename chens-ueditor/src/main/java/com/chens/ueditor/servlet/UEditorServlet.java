package com.chens.ueditor.servlet;

import com.baidu.ueditor.ActionEnter;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ueditor服务
 *
 * @author songchunlei@qq.com
 * @create 2018/4/25
 */
@WebServlet(name = "UEditorServlet", urlPatterns = "/UEditor")
public class UEditorServlet extends HttpServlet {



    @Value(value="classpath:config.json")
    private Resource resource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        /*资源服务方法（另一种解决思路）
        File file = ResourceUtils.getFile("classpath:config.json");
        File abFile = file.getAbsoluteFile();
        String abPath = file.getAbsolutePath();
        String parent = file.getParent();
        File parentFile = file.getParentFile();*/

        resp.setCharacterEncoding( "utf-8" );
        resp.setHeader("Content-Type" , "text/html");
        PrintWriter out = resp.getWriter();
        try {
            out.write( new ActionEnter( req, resource.getInputStream()).exec());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding( "utf-8" );
        resp.setHeader("Content-Type" , "text/html");
        PrintWriter out = resp.getWriter();
        try {
            out.write( new ActionEnter( req, resource.getInputStream()).exec());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
