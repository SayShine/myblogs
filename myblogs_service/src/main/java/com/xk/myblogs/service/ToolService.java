package com.xk.myblogs.service;

import com.xk.myblogs.client.entity.UserBlogs;

import java.util.List;

/**
 * @author: tian
 * @date: 2020/6/8 19:27
 */
public interface ToolService {
    /**
     * 根据电话号码生成四位数验证码 防止0000设置为String
     * @param telephone 电话号码
     * @return
     */
    String generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配 需要提前判空
     */
    boolean verifyAuthCode(String telephone, String authCode);

    /**
     * 根据用户名获取其博客
     * @param username
     * @return
     */
    List<UserBlogs> getMdListByUsername(String username);

    /**
     * 保存博客相关内容
     * @param userBlogs
     * @return
     */
    int savaMdList(UserBlogs userBlogs);

    /**
     * 批量删除博客内容
     * @param idsString
     * @return
     */
    int deleteMdList(String idsString);
}
