package com.xk.myblogs.client.entity.myblog;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * TABLE  tpg_user_blogs
 * MyBatis Generator Create
 */
public class UserBlogs implements Serializable {
    @ApiModelProperty(value = "博客主键id")
    private Long id;

    @ApiModelProperty(value = "关联用户id")
    private Long userId;

    @ApiModelProperty(value = "博客主题/标题")
    private String title;

    @ApiModelProperty(value = "点赞数")
    private Integer thumbnum;

    @ApiModelProperty(value = "阅读数")
    private Integer readnum;

    @ApiModelProperty(value = "博客备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "最后一次更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "博客状态：0->已删除；1->启用；2->禁用")
    private Integer status;

    @ApiModelProperty(value = "博客内容")
    private String content;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getThumbnum() {
        return thumbnum;
    }

    public void setThumbnum(Integer thumbnum) {
        this.thumbnum = thumbnum;
    }

    public Integer getReadnum() {
        return readnum;
    }

    public void setReadnum(Integer readnum) {
        this.readnum = readnum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", title=").append(title);
        sb.append(", thumbnum=").append(thumbnum);
        sb.append(", readnum=").append(readnum);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", status=").append(status);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}