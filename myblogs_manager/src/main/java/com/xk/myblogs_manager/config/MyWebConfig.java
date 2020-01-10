package com.xk.myblogs_manager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Xiong Kai
 * @date 2020年01月09日 16时29分53秒
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {

    @Value("${CROS_MAPPING}")
    private String mapping;

    @Value("${CROS_ALLOWED_ORIGINS}")
    private String allowedOrigins;

    @Value("${CROS_ALLOWED_METHOD}")
    private String[] allowedMethods;


    /**
     * 配置跨域请求
     * @param registry
     */
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(mapping).allowedOrigins(allowedOrigins).allowedMethods(allowedMethods).allowCredentials(true);
    }


}
