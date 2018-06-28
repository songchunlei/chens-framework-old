package com.chens.bpm.demo.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 会员
 *
 * @author songchunlei@qq.com
 * @create 2018/1/4
 */
public class Member implements Serializable{

    public static final String GOLD = "GOLD";
    public static final String SILVER = "SILVER";
    public static final String COOPER = "COOPER";

    private String  name;
    private String identity;
    /**
     * 消费金额
     */
    private BigDecimal amount;

    /**
     * 优惠后金额
     */
    private BigDecimal disAmount;

    public Member(String name, String identity,BigDecimal amount) {
        this.name = name;
        this.identity = identity;
        this.amount = amount;
    }

    public Member() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getDisAmount() {
        return disAmount;
    }

    public void setDisAmount(BigDecimal disAmount) {
        this.disAmount = disAmount;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", identity='" + identity + '\'' +
                ", amount=" + amount +
                ", disAmount=" + disAmount +
                '}';
    }
}
