package com.xk.myblogs.manager.controller;

import com.xk.myblogs.client.dto.LoginResultDto;
import com.xk.myblogs.client.entity.UserAdmin;
import com.xk.myblogs.common.enums.LoginStatusEnum;
import com.xk.myblogs.manager.vo.Result;
import com.xk.myblogs.service.UserAdminService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xiong Kai
 * @date 2020年01月17日 13时30分25秒
 */
@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private UserAdminService userAdminService;

    @GetMapping
    @RequestMapping("/toLogin")
    public Result<String> toTransalte(@Param(value = "username") String username,
                                      @Param(value = "password") String password) {
        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            return Result.error(LoginStatusEnum.LOGIN_PARAM_ERROR.getMsg());
        }
        LoginResultDto loginResultDto = userAdminService.toLogin(username,password);
        if (loginResultDto==null){
            return Result.error(LoginStatusEnum.LOGIN_PARAM_ERROR.getMsg());
        }else if(LoginStatusEnum.LOGIN_SUCCESS.getType().equals(loginResultDto.getStatus())){
            return Result.ok(loginResultDto.getToken());
        }else{
            return Result.error(loginResultDto.getMsg());
        }
    }

    @GetMapping
    @RequestMapping("/helloWorld")
    public Result<String> sayHello() {
        return Result.ok("hello world");
    }
}
