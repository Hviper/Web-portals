package com.example.springbootdemo1.Response;

/**
 * @author 拾光
 * @version 1.0
 */
public class Result <T>{
    T data;
    Meta meta;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public static <T> Result<T> success(T data, String msg, int code){
        Result<T> res = new Result<>();
        res.data = data;
        res.meta.code = code;
        res.meta.msg = msg;
        return res;
    }

    public static <T> Result<T> fail(T data,String msg,int code){
        Result<T> res = new Result<>();
        res.data = data;
        res.meta.code = code;
        res.meta.msg = msg;
        return res;
    }


    public Result() {
        meta = new Meta();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result(T data, String msg, int code) {
        this.data = data;
        meta = new Meta(msg, code);
        this.meta.msg = msg;
        this.meta.code = code;
    }
}
class Meta{
    String msg;
    int code;

    public Meta() {
    }

    public Meta(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
