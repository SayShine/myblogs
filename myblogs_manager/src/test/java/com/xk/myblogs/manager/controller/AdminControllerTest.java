package com.xk.myblogs.manager.controller;

import com.xk.myblogs.client.dto.LoginResultDto;
import com.xk.myblogs.common.enums.LoginStatusEnum;
import com.xk.myblogs.manager.ApplicationConfig;
import com.xk.myblogs.manager.vo.Result;
import com.xk.myblogs.service.UserAdminService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import static org.junit.Assert.*;

public class AdminControllerTest extends ApplicationConfig {

    @Autowired
    private AdminController adminController;

    @Test
    public void sayHello() {
        adminController.sayHello();
    }

    @Test
    public void toLogin() {
        adminController.toLogin("admin","111");
        adminController.toLogin("admissn","111");
        adminController.toLogin("","111");
    }

    @Test
    public void toRegist() {
    }

    @Test
    public void refreshToken() {
        adminController.refreshToken("admin");

    }

}