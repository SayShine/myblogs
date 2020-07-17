package com.xk.myblogs.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.xk.myblogs.client.entity.nosql.User;

import java.util.List;

/**
 * @author: tian
 * @date: 2020/7/12 18:58
 */
public interface NosqlService {
    /**
     * 保存用户信息
     * @param user
     * @return
     */
    void savaMdList(User user);

    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 条件查询
     * @param userName 用户名
     * @param note 备注
     * @param skip 跳过数量
     * @param limit 返回数量
     * @return
     */
    List<User> findUser(String userName, String note, Integer skip, Integer limit);

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    DeleteResult deleteUserById(Long id);

    /**
     * 更新文档
     * @param id
     * @param userName
     * @param note
     * @return
     */
    UpdateResult updateUser(Integer id, String userName, String note);


}
