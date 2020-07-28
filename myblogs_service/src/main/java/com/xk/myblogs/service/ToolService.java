package com.xk.myblogs.service;

import com.xk.myblogs.client.entity.myblog.UserBlogs;
import com.xk.myblogs.client.entity.tscxk.StudyUrl;

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
     * @param userBlogs 博客内容
     * @return
     */
    Integer updateMdList(UserBlogs userBlogs);

    /**
     * 批量删除博客内容
     * @param ids
     * @return
     */
    Integer deleteMdList(Long[] ids);

    /**
     * 根据用户名新增博客内容
     * @param username
     * @param userBlogs
     * @return
     */
    Integer insertMdList(String username, UserBlogs userBlogs);

    /**
     * 异步模拟生成报表的方法
     */
    void generateRepost();

    /**
     * 获取学习网址
     * @return
     */
    List<StudyUrl> getStudyList();

    /**
     * 插入学习内容
     * @param studyUrl
     * @return
     */
    Integer insertStudyList(StudyUrl studyUrl);

    /**
     * 更新单个（包括删除）
     * @param studyUrl
     * @return
     */
    Integer updateStudyList(StudyUrl studyUrl);

    /**
     * 批量更新（包括删除）
     * @param ids
     * @return
     */
    Integer updateAllStudyList(Long[] ids);

}
