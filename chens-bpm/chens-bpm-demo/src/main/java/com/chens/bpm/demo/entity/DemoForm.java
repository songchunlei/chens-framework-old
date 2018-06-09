package com.chens.bpm.demo.entity;

import java.io.Serializable;

/**
 * 测试表单
 *
 * @author songchunlei@qq.com
 * @create 2018/6/9
 */
public class DemoForm implements Serializable{
    private String id;
    private String name;
    private Integer age;

    public DemoForm(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public DemoForm() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
