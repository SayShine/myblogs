package com.xk.myblogs.client.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * TABLE  tpg_user
 * MyBatis Generator Create
 */
public class UserAdmin implements Serializable {
    /**
     * ID
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Long id;

    /**
     * NAME
     * 姓名
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String name;

    /**
     * ACCOUNT
     * 账号
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String account;

    /**
     * PASSWORD
     * 密码
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String password;

    /**
     * CREATE_TIME
     * 创建时间
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Date createTime;

    /**
     * CREATOR
     * 创建人
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Long creator;

    /**
     * UPDATE_TIME
     * 更新时间
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Date updateTime;

    /**
     * UPDATOR
     * 更新人
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Long updator;

    /**
     * STATUS
     * 状态【0：已删除；1：可使用；2：禁用】
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Integer status;

    /**
     * COMMENTS
     * 备注
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String comments;

    /**
     * GENDER
     * 性别【1：女；2，男】
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String gender;

    /**
     * EMAIL
     * 邮箱
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String email;

    /**
     * REALNAME
     * 真实姓名
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String realname;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdator() {
        return updator;
    }

    public void setUpdator(Long updator) {
        this.updator = updator;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}