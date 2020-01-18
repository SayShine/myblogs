package com.xk.myblogs.client.dto;

import java.io.Serializable;

/**
 * @author Xiong Kai
 * @date 2020年01月17日 14时23分06秒
 */
public class LoginResultDto implements Serializable {
    /*登陆状态*/
    private Integer status;
    /*登陆消息*/
    private String msg;
    /*返回的token*/
    private String token;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
