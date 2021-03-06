package com.xk.myblogs.manager.sercurity;

import com.xk.myblogs.common.utils.JwtUtil;
import com.xk.myblogs.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * jwt登录授权过滤器
 * @author Xiong Kai
 * @date 2020年01月16日 12时37分40秒
 */
public class JwtAuthenticationTokenFilter  extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private RedisService redisService;

    @Autowired
    private JwtUtil jwtUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    //redis中 验证码字段和时间限制
    @Value("${redis.key.prefix.authToken}")
    private String REDIS_KEY_PREFIX_AUTH_TOKEN;
    @Value("${redis.key.expire.authToken}")
    private Long AUTH_TOKEN_EXPIRE_SECONDS;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        //尝试获取token
        String authHeader = request.getHeader(this.tokenHeader);
        if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
            //获取token
            String authToken = authHeader.substring(this.tokenHead.length());// The part after "Bearer "
            String username = jwtUtil.getUserNameFromToken(authToken);

            //上下文中找不到token
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (jwtUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    LOGGER.info("authenticated user:{}", username);
                    LOGGER.info("User has auth: {}", userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }else{
                //从redis中寻找token


                request.setAttribute("expiredFlag",true);
            }
        }
        chain.doFilter(request, response);
    }
}
