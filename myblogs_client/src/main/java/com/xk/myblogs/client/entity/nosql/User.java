package com.xk.myblogs.client.entity.nosql;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @author: tian
 * @date: 2020/7/12 18:39
 */
@Document
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "MongoDB文档编号  主键")
    @Id
    @NotEmpty
    private Long id;

    @ApiModelProperty
    @Field("user_name")
    private String userName;

    @ApiModelProperty("备注")
    private String note;

    @ApiModelProperty("用户列表")
    private List<Role> roles;

}
