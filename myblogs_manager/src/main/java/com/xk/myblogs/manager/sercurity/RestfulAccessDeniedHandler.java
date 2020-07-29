package com.xk.myblogs.manager.sercurity;

import com.alibaba.fastjson.JSON;
import com.xk.myblogs.manager.vo.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 配置登陆失败的返回结果
 * @author Xiong Kai
 * @date 2020年01月16日 10时39分23秒
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler{
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().println(JSON.toJSONString(Result.error(String.valueOf(HttpServletResponse.SC_FORBIDDEN),"权限不匹配："+e.getMessage())));
        response.getWriter().flush();
    }
}
