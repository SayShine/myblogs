package com.xk.myblogs.common.enums;

/**
 * 登录状态枚举
 * @author Xiong Kai
 * @date 2020年01月17日 13时57分26秒
 */
public enum LoginStatusEnum {
    /**
     * 系统异常
     */
    LOGIN_SYSTEM_ERROR(0,"系统异常"),
    /**
     * 用户名或密码为空
     */
    LOGIN_PARAM_ERROR(1,"用户名或密码为空"),
    /**
     * 参数检验失败
     */
    LOGIN_VALIDATE_FAILED(404, "参数检验失败"),
    /**
     * 用户名不存在
     */
    LOGIN_USER_ERROR(2,"用户名不存在"),
    /**
     * 用户名或密码错误
     */
    LOGIN_PASSWORD_ERROR(3,"用户名或密码错误"),
    /**
     * 登陆成功
     */
    LOGIN_SUCCESS(4,"登陆成功")
    ;

    private Integer type;
    private String msg;

    private LoginStatusEnum(Integer type, String msg){
        this.type = type;
        this.msg = msg;
    }

    public Integer getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }
}
