package com.xk.myblogs.manager.controller;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xk.myblogs.client.entity.UserBlogs;
import com.xk.myblogs.manager.vo.Result;
import com.xk.myblogs.common.utils.Md5Util;
import com.xk.myblogs.service.ToolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiongkai
 * @date 2020年01月09日 14时08分08秒
 **/
@RestController
@RequestMapping("tool")
@Api(tags = "ToolController", description = "祖安工具")
public class ToolController {
    @Autowired
    private ToolService toolService;

    @GetMapping("/toTranslate")
    @ApiOperation("祖安语言翻译器")
    public Result<String> toTransalte() {
        return Result.ok("我是带弟弟柯西");
    }

    @GetMapping("/toEncode")
    @ApiOperation("md5加密功能")
    public Result<String> toEncoding(@RequestParam String code) {
        String result = Md5Util.getMD5(code);
        return Result.ok(result);
    }

    @GetMapping("/getAuthCode")
    @ApiOperation("获取验证码")
    public Result<String> getAuthCoded(@RequestParam String telephone){
        if("".equals(telephone)){
            return Result.error("请输入手机号码");
        }
        String authCode = toolService.generateAuthCode(telephone);
        return Result.ok(authCode);
    }

    @GetMapping("/verifyAuthCode")
    @ApiOperation("比对验证码是否正确")
    public Result<String> verifyAuthCode(@RequestParam(value = "telephone") String telephone,
                                 @RequestParam(value = "authCode") String authCode){
        if("".equals(authCode)){
            return Result.error("请输入验证码");
        }
        boolean result = toolService.verifyAuthCode(telephone,authCode);
        return result?Result.ok("验证码校验成功"):Result.error("验证码校验失败");
    }

    @GetMapping("/MdList/{username}")
    @ApiOperation("根据用户名获取博客列表")
    public Result<List<UserBlogs>> getMdListByUsername(@PathVariable String username){
        List<UserBlogs> userBlogsList = toolService.getMdListByUsername(username);
        return Result.ok(userBlogsList);
    }

    @PostMapping("/MdList")
    @ApiOperation("更新用户博客内容")
    public Result updateMdList(@RequestBody String jsonString) {
        //根据json字符串进行博客内容新增或更改
        int count = toolService.savaMdList(jsonString);
        return count>0?Result.ok(count):Result.error("更新失败");
    }

    @PutMapping("/MdList")
    @ApiOperation("根据用户名新增用户博客内容")
    public Result insertMdList(@RequestBody String jsonString) {
        //根据json字符串进行博客内容新增或更改
        int count = toolService.savaMdList(jsonString);
        return count>0?Result.ok(count):Result.error("更新失败");
    }

    @GetMapping("/deleteMdList")
    @ApiOperation("批量删除博客内容")
    public Result deleteMdList(@RequestParam(value = "idsString") String idsString){
        int count = toolService.deleteMdList(idsString);
        return count>0?Result.ok(count):Result.error("删除失败");
    }

}
