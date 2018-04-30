package com.chens.share.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.chens.core.web.BaseController;
import com.chens.share.service.IShareService;
import com.chens.share.entity.Share;


import com.chens.core.web.BaseWebController;

/**
 *
 * 站内分享 控制器
 *
 * @author chunlei.song@live.com
 * @create 2018-04-29
 */
public abstract class AbstractShareController extends BaseWebController<IShareService,Share> {


}
