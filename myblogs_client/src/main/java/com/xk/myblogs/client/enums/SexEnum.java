package com.xk.myblogs.client.enums;

/**
 * 性别枚举
 * @author: tian
 * @date: 2020/7/8 21:39
 */
public enum  SexEnum {
    /**
     * 男
     */
    MALE(1,"男"),
    /**
     * 女
     */
    FEMALE(2,"女");

    private int id;
    private String name;

    private SexEnum(int id, String name){
        this.id = id;
        this.name = name;
    }

    /**
     * 根据id返回性别
     * @param id
     * @return
     */
    public static SexEnum getEnumById(int id){
        for (SexEnum sex : SexEnum.values()) {
            if(sex.getId() == id){
                return sex;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
