package com.chens.bpm.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.chens.bpm.service.IWfEngineService;
import com.chens.bpm.vo.MyDoneTask;
import com.chens.bpm.vo.MyStartProcessInstance;
import com.chens.bpm.vo.MyTodoTask;
import com.chens.bpm.web.BaseBpmController;
import com.chens.core.constants.CommonConstants;
import com.chens.core.context.BaseContextHandler;
import com.chens.core.vo.PageVo;
import com.chens.core.vo.QueryPageEntity;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 流程服务页
 *
 * @author songchunlei@qq.com
 * @create 2018/4/15
 */
@Controller
@RequestMapping("/bpmController")
public class BpmController extends BaseBpmController {

}
