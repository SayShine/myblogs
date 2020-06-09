package com.xk.myblogs.service;

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
}
