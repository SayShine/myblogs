package com.xk.myblogs.client.vo;

import java.io.Serializable;

/**
 * 用于传输数据的model
 * @author xiongkai
 * @date 2020年01月09日 13时59分48秒
 **/
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String SUCCESS = "0";
    private static final String FAIL = "1";
    private static final String DEFAULT_SUCCESS_MSG = "SUCCESS";
    private static final String DEFAULT_FAIL_MSG = "FAILURE";

    private String code;
    private String msg;
    private T body;

    private Result(){
    }
    private Result(String code, String msg, T body){
        this.code = code;
        this.msg = msg;
        this.body = body;
    }

    public static Result ok(){
        return new Result(Result.SUCCESS,Result.DEFAULT_SUCCESS_MSG,null);
    }
    public static Result ok(Object body){
        return new Result(Result.SUCCESS,Result.DEFAULT_SUCCESS_MSG,body);
    }

    public static Result error(){
        return new Result(Result.FAIL,DEFAULT_FAIL_MSG,null);
    }
    public static Result error(String msg){
        return new Result(Result.FAIL,msg,null);
    }
    public static Result error(String code, String msg){
        return new Result(code,msg,null);
    }
}
