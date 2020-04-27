package com.hcis.ipanther.common.utils;

public class BaseApi {

    public int code = ApiCode.SUCCESS;

    public String msg;

    public Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setError(String errMsg){
        this.setCode(ApiCode.ERROR);
        this.setMsg(errMsg);
    }
}
