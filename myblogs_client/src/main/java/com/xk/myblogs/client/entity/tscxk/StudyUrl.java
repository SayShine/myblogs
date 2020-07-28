package com.xk.myblogs.client.entity.tscxk;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 
 *   学习网址
 *
 * TABLE  study_url
 * MyBatis Generator Create
 */
public class StudyUrl implements Serializable {
    @ApiModelProperty(value = "学习id")
    private Long id;

    @ApiModelProperty(value = "网站备注")
    private String comment;

    @ApiModelProperty(value = "网站url")
    private String url;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", comment=").append(comment);
        sb.append(", url=").append(url);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}