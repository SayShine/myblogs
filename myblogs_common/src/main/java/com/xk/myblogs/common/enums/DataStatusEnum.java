package com.xk.myblogs.common.enums;

/**
 * 数据库数据状态枚举
 * @author Xiong Kai
 * @date 2020年01月17日 14时29分39秒
 */
public enum DataStatusEnum {

    /*已删除*/
    IS_DEL(0,"已删除"),
    /*可使用*/
    USE_ENABLE(1,"可使用"),
    /*禁用*/
    USE_UNABLE(2,"禁用");


    private Integer type;
    private String msg;

    private DataStatusEnum(Integer type, String msg){
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
