package com.xk.myblogs.manager.controller;

import com.xk.myblogs.client.dto.LoginResultDto;
import com.xk.myblogs.client.entity.UserAdmin;
import com.xk.myblogs.common.enums.LoginStatusEnum;
import com.xk.myblogs.manager.vo.Result;
import com.xk.myblogs.service.UserAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Xiong Kai
 * @date 2020年01月17日 13时30分25秒
 */
@RestController
@RequestMapping("admin")
@Api(tags = "AdminController",description = "用户登录")
public class AdminController {
    @Autowired
    private UserAdminService userAdminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @PostMapping("/toLogin")
    @ApiOperation("会员登录")
    public Result<String> toLogin(@RequestParam(value = "username") String username,
                                      @RequestParam(value = "password") String password) {
        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            return Result.error(LoginStatusEnum.LOGIN_PARAM_ERROR.getMsg());
        }
        LOGGER.info("username:"+username+", password:"+password);
        LoginResultDto loginResultDto = userAdminService.toLogin(username,password);
        if (loginResultDto==null){
            return Result.error(LoginStatusEnum.LOGIN_PARAM_ERROR.getMsg());
        }else if(LoginStatusEnum.LOGIN_SUCCESS.getType().equals(loginResultDto.getStatus())){
            LOGGER.info("登录token：{}",loginResultDto.getToken());
            return Result.ok(loginResultDto.getToken());
        }else{
            return Result.error(loginResultDto.getMsg());
        }
    }

    @ApiOperation("会员注册")
    @PostMapping("/toRegister")
    public Result<UserAdmin> toRegist(@RequestBody@Valid UserAdmin userAdminParam,BindingResult result){
        if(result.hasErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            StringBuilder sb = new StringBuilder();
            fieldErrors.forEach(fieldError -> {
                //日志打印不符合校验的字段名和错误提示
                LOGGER.error("error field is : {} ,message is : {}",fieldError.getField(), fieldError.getDefaultMessage());
                sb.append(fieldError.getDefaultMessage()+" ");
            });
            return Result.error("注册失败,错误信息: " + sb.toString());
        }
        UserAdmin userAdmin = userAdminService.register(userAdminParam);
        if(userAdmin==null){
            return Result.error("注册失败");
        }
        return Result.ok(userAdmin);
    }

    @ApiOperation(value = "刷新token（从redis中判断是否存在才能刷新）")
    @GetMapping("/refreshToken")
    @ResponseBody
    public Result refreshToken(@RequestParam(value = "username") String username) {
        if(StringUtils.isEmpty(username)){
            return Result.error("用户未登录");
        }
        String refreshedToken = userAdminService.refreshToken(username);
        if (refreshedToken == null) {
            return Result.error("token已经过期！");
        }
        LOGGER.info("刷新的token：{}",refreshedToken);
        return Result.ok(refreshedToken);
    }

    @GetMapping
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ApiOperation("纯属测试")
    public Result<String> sayHello() {
        return Result.ok("20200701晚上");
    }
}
