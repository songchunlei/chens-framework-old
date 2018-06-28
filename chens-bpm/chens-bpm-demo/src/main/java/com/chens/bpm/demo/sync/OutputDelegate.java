package com.chens.bpm.demo.sync;

import com.chens.bpm.demo.entity.Member;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 输出结果
 *
 * @author songchunlei@qq.com
 * @create 2018/1/6
 */
@Component
public class OutputDelegate implements JavaDelegate,Serializable {

    @Override
    public void execute(DelegateExecution execution) {
        List<Member> members = (List<Member>) execution.getVariable("members");
        for (Member m:members) {
            System.out.println("输出:"+m.toString());
        }

    }
}
