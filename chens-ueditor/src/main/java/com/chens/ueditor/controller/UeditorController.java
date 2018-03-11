package com.chens.ueditor.controller;

import com.baidu.ueditor.ActionEnter;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 通用服务
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/11
 */
@Controller
public class UeditorController {

    @Autowired
    private Environment environment;

    @RequestMapping("/")
    private String showPage(){
        return "index";
    }

    @RequestMapping(value="/config")
    public void config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    /*
    参考 https://github.com/codingapi/ueditor.git
    private Logger logger = LoggerFactory.getLogger(UeditorController.class);

    private String rootPath;

    private String projectPath = null;

    @Autowired
    private Environment environment;

    private final static String staticPath = "static/";

    public UeditorController() {
        String path  = UeditorController.class.getClassLoader().getResource("config.json").getPath();
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

    @RequestMapping("/exec")
    public void exec(HttpServletRequest request, HttpServletResponse response, PrintWriter out){
        response.setHeader("Content-Type" , "text/html");
        logger.info("rootPath->"+rootPath+",staticPath->"+staticPath+",projectPath->"+getProjectPath());
        out.write( new ActionEnter( request, rootPath,staticPath,getProjectPath()).exec());
    }
    */
}
