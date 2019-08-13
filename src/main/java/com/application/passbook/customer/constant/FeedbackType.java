package com.application.passbook.customer.constant;

/**
 * <h1>评论类型</h1>
 */
public enum FeedbackType {

    PASS(1, "针对优惠券的评论"),
    APP(2, "针对卡包APP的评论");

    private Integer code;

    private String desc;

    FeedbackType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }}
