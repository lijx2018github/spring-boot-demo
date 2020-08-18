package com.lijx.demo.common.enums;

public enum ResultEnum {

    SUCCESS("0000", "SUCCESS"),
    OK("0001", "OK"),
    PARAMS_ERR("4000", "参数错误"),
    SERVICE_ERR("2000", "业务异常"),
    AUTH_ERR("6000", "登陆认证异常"),
    OTHERS_ERR("9999", "其他异常");

    // 构造方法
    private ResultEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    private String code;
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
