package com.xk.myblogs.manager.controller;



import com.xk.myblogs.client.entity.myblog.UserBlogs;
import com.xk.myblogs.client.entity.nosql.User;
import com.xk.myblogs.client.entity.tscxk.StudyUrl;
import com.xk.myblogs.common.annotion.TestAnnotion;
import com.xk.myblogs.manager.vo.Result;
import com.xk.myblogs.common.utils.Md5Util;
import com.xk.myblogs.service.ActiveMqService;
import com.xk.myblogs.service.ToolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private ActiveMqService activeMqService;

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

    //----------------我的博客start
    @GetMapping("/MdList/{username}")
    @ApiOperation("根据用户名获取博客列表")
    public Result<List<UserBlogs>> getMdListByUsername(@PathVariable String username){
        List<UserBlogs> userBlogsList = toolService.getMdListByUsername(username);
        return Result.ok(userBlogsList);
    }

    @PutMapping("/MdList")
    @ApiOperation("更新用户博客内容")
    public Result updateMdList(@RequestBody UserBlogs userBlogs) {
        System.out.println(userBlogs);
        //根据json字符串进行博客内容新增或更改
        int count = toolService.updateMdList(userBlogs);
        return count>0?Result.ok(count):Result.error("更新失败");
    }

    @PostMapping("/MdList/{username}")
    @ApiOperation("根据用户名新增用户博客内容")
    public Result insertMdList(@PathVariable String username, @RequestBody UserBlogs userBlogs) {
        //根据json字符串进行博客内容新增或更改
        int count = toolService.insertMdList(username, userBlogs);
        return count>0?Result.ok(count):Result.error("新增失败");
    }

    @PutMapping("/deleteMdList")
    @ApiOperation("批量删除博客内容")
    public Result deleteMdList(@RequestBody Long[] ids){
        Integer count = toolService.deleteMdList(ids);
        return count>0?Result.ok(count):Result.error("删除失败");
    }
    //----------------我的博客end

    @GetMapping("/asyncTest")
    @ApiOperation("异步请求")
    public Result asyncTest(){
        System.out.println("当前请求线程名称" + "[" + Thread.currentThread().getName() + "]");
        toolService.generateRepost();
        return Result.ok("调用成功");
    }

    @GetMapping("/sendMsg/{msg}")
    @ApiOperation("activemq发送消息")
    public Result sendMqMsg(@PathVariable String msg){
        activeMqService.sendMsg(msg);
        return Result.ok("发送成功");
    }

    @PostMapping("/sendMsg")
    @ApiOperation("activemq发送对象消息")
    public Result sendEntityMqMsg(@RequestBody User user){
        activeMqService.sendUser(user);
        return Result.ok("发送成功");

    }

    //学习博客start--------------------------------------------------
    @GetMapping("/studyList")
    @ApiOperation("获取学习网站列表")
    public Result<List<StudyUrl>> getStudyList(){
        List<StudyUrl> studyUrlList = toolService.getStudyList();
        return Result.ok(studyUrlList);
    }

    @PreAuthorize("hasAuthority('root')")
    @PutMapping("/studyList")
    @ApiOperation("单个修改状态(目前仅用于删除)")
    public Result updateStudyList(@RequestBody StudyUrl studyUrl){
        Integer count = toolService.updateStudyList(studyUrl);
        return count>0?Result.ok(count):Result.error("删除失败");
    }

    @PreAuthorize("hasAuthority('root')")
    @PostMapping("/studyList")
    @ApiOperation("新增博客")
    public Result insertStudyList(@RequestBody StudyUrl studyUrl) {
        //根据json字符串进行博客内容新增或更改
        Integer count = toolService.insertStudyList(studyUrl);
        return count>0?Result.ok(count):Result.error("新增失败");
    }

    @PutMapping("/allStudyList")
    @ApiOperation("批量修改状态(目前仅用于删除)")
    public Result updateAllStudyList(@RequestBody Long[] ids){
        Integer count = toolService.updateAllStudyList(ids);
        return count>0?Result.ok(count):Result.error("修改失败");
    }

    //学习博客end----------------------------------------------------

}
