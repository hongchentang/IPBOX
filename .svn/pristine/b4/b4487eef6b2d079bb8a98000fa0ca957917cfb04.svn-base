package com.hcis.ipr.common.utils;

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

    public void setError(String msg){
        this.code = ApiCode.ERROR;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BaseApi{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
