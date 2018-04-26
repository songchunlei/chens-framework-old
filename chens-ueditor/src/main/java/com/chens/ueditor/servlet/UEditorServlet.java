package com.chens.ueditor.servlet;

import com.baidu.ueditor.ActionEnter;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
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


    @Autowired
    private Environment environment;

    private final static String staticPath = "";

    private Logger logger = LoggerFactory.getLogger(UEditorServlet.class);

    private String rootPath;

    private String projectPath = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding( "utf-8" );
        resp.setHeader("Content-Type" , "text/html");
        PrintWriter out = resp.getWriter();

        try {
            out.write( new ActionEnter( req, rootPath,staticPath,getProjectPath()).exec());
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
            out.write( new ActionEnter( req, rootPath,staticPath,getProjectPath()).exec());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public UEditorServlet() {
        String path  = UEditorServlet.class.getClassLoader().getResource("config.json").getPath();
        logger.info("path->"+path);
        File file =  new File(path);
        if(file.getParentFile().isDirectory()) {
            rootPath = new File(path).getParent()+"/";
        }else{
            rootPath = new File(path).getParentFile().getParent()+"/";
            rootPath = rootPath.replace("file:","");
        }
    }

    private String getProjectPath(){
        if(null==projectPath) {
            String val = environment.getProperty("server.context-path", "");
            if ("".equals(val)) {
                val = environment.getProperty("server.contextPath", "");
                if ("".equals(val)) {
                    projectPath = "";
                    return projectPath;
                }
            }
            projectPath = val.replace("/", "") + "/";
        }
        return projectPath;
    }
}
