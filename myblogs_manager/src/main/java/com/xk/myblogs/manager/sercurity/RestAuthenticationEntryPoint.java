package com.xk.myblogs.manager.sercurity;


import com.alibaba.fastjson.JSON;
import com.xk.myblogs.manager.vo.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 配置未登录或登录失效的返回结果
 * @author Xiong Kai
 * @date 2020年01月16日 10时52分18秒
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Integer status = request.getAttribute("expiredFlag")!=null?HttpServletResponse.SC_PAYMENT_REQUIRED:HttpServletResponse.SC_UNAUTHORIZED;
        response.setStatus(status);
        //response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().println(JSON.toJSONString(Result.error(String.valueOf(HttpServletResponse.SC_UNAUTHORIZED),"请登录！")));
        response.getWriter().flush();
    }
}
