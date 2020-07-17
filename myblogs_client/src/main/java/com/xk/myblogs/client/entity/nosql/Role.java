package com.xk.myblogs.client.entity.nosql;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @author: tian
 * @date: 2020/7/12 18:49
 */
@Document
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Field("role_name")
    private String roleName;

    private String note;
}
