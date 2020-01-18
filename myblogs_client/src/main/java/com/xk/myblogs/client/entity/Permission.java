package com.xk.myblogs.client.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * TABLE  tpg_authority
 * MyBatis Generator Create
 */
public class Permission implements Serializable {
    /**
     * ID
     * 权限id
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Long id;

    /**
     * NAME
     * 权限名
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String name;

    /**
     * PID
     * 父权限id
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Long pid;

    /**
     * DESCRIPTION
     * 权限描述
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String description;

    /**
     * CREATOR
     * 创建人
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Long creator;

    /**
     * CREATE_TIME
     * 创建时间
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Date createTime;

    /**
     * UPDATER
     * 更新人
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Long updater;

    /**
     * UPDATE_TIME
     * 更新时间
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Date updateTime;

    /**
     * STATUS
     * 状态【0：已删除；1：可使用；2：禁用】
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Integer status;

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

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getValue() {
        return name;
    }
}