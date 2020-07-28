package com.xk.myblogs.manager.config;

import com.xk.myblogs.client.entity.myblog.Permission;
import com.xk.myblogs.client.entity.myblog.UserAdmin;
import com.xk.myblogs.manager.sercurity.*;
import com.xk.myblogs.service.UserAdminService;
import com.xk.myblogs.service.dto.UserAdminDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * HTTP配置(jwt会话管理，跨域，登录认证，以及权限认证)
 * @author Xiong Kai
 * @date 2020年01月16日 10时36分41秒
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private UserAdminService adminService;

    @Value("${CROS_MAPPING}")
    private String mapping;

    @Value("${CROS_ALLOWED_HEADERS}")
    private String[] allowedHeaders;

    @Value("${CROS_ALLOWED_ORIGINS}")
    private String[] allowedOrigins;

    @Value("${CROS_ALLOWED_METHOD}")
    private String[] allowedMethods;

    /**
     * 配置SpringSecurity
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                //使用jwt做会话管理
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                //权限认证
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, // 允许对于网站静态资源的无授权访问
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/swagger**/**",
                        "/configuration/**",
                        "/v2/api-docs/**"
                )
                .permitAll()
                .antMatchers("/admin/refreshToken")
                .authenticated()
                .antMatchers("/admin/**")// 对登录注册要允许匿名访问
                .permitAll()
                .antMatchers(HttpMethod.OPTIONS)//跨域请求会先进行一次options请求
                .permitAll()
                .anyRequest()//除上面外的所有请求全部需要鉴权认证
//                .authenticated()
                .permitAll()

                //配置跨域
                .and()
                .cors()//允许跨域

                //配置缓存
                .and()
                .headers()
                .cacheControl();//禁用缓存

        // 添加JWT filter
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义未授权和未登录结果返回
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    //loadUserByUsername里面只有一个接口 loadUserByUsername 用于加载用户信息 函数式编程
    public UserDetailsService userDetailsService() {
        return username -> {
            UserAdmin userAdmin = adminService.getUserAdminByUsername(username);
            if(userAdmin!=null){
                List<Permission> permissionList = adminService.getPermissionsByUserid(userAdmin.getId());
                return new UserAdminDetail(userAdmin,permissionList);
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    /**
     * 这里是配置跨域请求的bean
     */
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList(allowedOrigins));
        configuration.setAllowedMethods(Arrays.asList(allowedMethods));
//        configuration.setAllowedHeaders(Arrays.asList(allowedHeaders));

        //配置请求源和请求头
        configuration.addAllowedOrigin("http://localhost:9527");
        configuration.addAllowedOrigin("http://localhost");

        //配置正式环境
        configuration.addAllowedOrigin("http://47.103.137.193");
        configuration.addAllowedOrigin("http://www.wangmin520.com");
        configuration.addAllowedOrigin("http://wangmin520.com");

        configuration.addAllowedOrigin("http://39.107.247.2");
        configuration.addAllowedOrigin("http://www.lvmz521.com");
        configuration.addAllowedOrigin("http://lvmz521.com");

        //配置请求头
        configuration.addAllowedHeader("Authorization");
        configuration.addAllowedHeader("Content-Type");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(mapping, configuration);
        return source;
    }


}
