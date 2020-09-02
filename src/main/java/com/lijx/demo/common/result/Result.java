package com.lijx.demo.common.result;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import com.lijx.demo.common.enums.ResultEnum;

public class Result {
    private String code;
    private String description;
    private Object data;


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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /////////// 构造方法 ///////////

    public Result() {
        this.code = ResultEnum.SUCCESS.getCode();
        this.description = ResultEnum.SUCCESS.getDescription();
    }

    public Result(Object data) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.description = ResultEnum.SUCCESS.getDescription();
        this.data = data;
    }

    public Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.description = resultEnum.getDescription();
    }

    public Result(ResultEnum resultEnum, String description) {
        this.code = resultEnum.getCode();
        this.description = description;
    }

    public Result(ResultEnum resultEnum, String description, Object data) {
        this.code = resultEnum.getCode();
        this.description = description;
        this.data = data;
    }



    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", data=" + data +
                '}';
    }

}
