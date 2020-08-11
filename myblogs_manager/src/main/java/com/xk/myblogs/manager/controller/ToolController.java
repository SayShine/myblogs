package com.xk.myblogs.manager.controller;



import com.xk.myblogs.client.entity.myblog.Product;
import com.xk.myblogs.client.entity.myblog.UserBlogs;
import com.xk.myblogs.client.entity.nosql.User;
import com.xk.myblogs.client.entity.tscxk.StudyUrl;
import com.xk.myblogs.manager.vo.Result;
import com.xk.myblogs.common.utils.Md5Util;
import com.xk.myblogs.service.ActiveMqService;
import com.xk.myblogs.service.OrderService;
import com.xk.myblogs.service.ToolService;
import com.xk.myblogs.service.dto.UserAdminDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author xiongkai
 * @date 2020年01月09日 14时08分08秒
 **/
@RestController
@RequestMapping("tool")
@Api(tags = "ToolController", description = "祖安工具")
public class ToolController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ToolController.class);

    @Autowired
    private ToolService toolService;

    @Autowired
    private ActiveMqService activeMqService;

    @Autowired
    private OrderService orderService;

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

    @GetMapping("/studyList/{keywords}")
    @ApiOperation("根据关键字获取学习网站列表")
    public Result<List<StudyUrl>> getStudyListByKeyWords(@PathVariable String keywords){
        List<StudyUrl> studyUrlList = toolService.getStudyListByKeyWords(keywords);
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

    //并发抢购start----------------------------------------------------
    @GetMapping("/product/{id}")
    @ApiOperation("根据id获取商品信息")
    public Result<Product> getProductById(@PathVariable Long id){
        Product product = toolService.getProductById(id);
        return product==null ? Result.error("查询不到结果") : Result.ok(product);
    }

    @PostMapping("/product/puchase/{productid}/{quantity}")
    @ApiOperation("购买对应商品的对应数量   可选择使用CompletableFuture异步回调提升速度")
//    @PreAuthorize("hasAuthority('root')")
    public Result<String> purchaseProduct(@PathVariable Long productid,
                                  @PathVariable int quantity) {
        //当前登录用户id
        UserAdminDetail userAdminDetail = (UserAdminDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userid = userAdminDetail.getUserId();
        Map<String, Object> map = new HashMap<>();
        map.put("isSuccess", false);

        //同步处理请求  100个请求 耗时7-8s
//        Boolean isSuccess = toolService.purchaseProduct(productid, quantity, userid);
//        map.put("isSuccess", isSuccess);

        //异步并发请求 因为只有一个事务 实际上和同步处理请求耗时一样  下面的才能充分体现异步回调的好处
        CompletableFuture<Boolean> purchase = toolService.purchaseProductSupply(productid, quantity, userid);
        try {
            purchase.whenComplete((v, t) -> {
                map.put("isSuccess", false);
            }).exceptionally(exception -> {
                System.out.println("捕获异常： " + exception);
                return false;
            }).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //异步回调例子
//        CompletableFuture<Boolean> purchase = toolService.purchaseProductSupply(productid, quantity, userid);
//        CompletableFuture<Boolean> purchase2 = toolService.purchaseProductSupply(productid, quantity, userid);
//        CompletableFuture<Boolean> purchase3 = toolService.purchaseProductSupply(productid, quantity, userid);
//        CompletableFuture<Boolean> purchase4 = toolService.purchaseProductSupply(productid, quantity, userid);
//        purchase.whenComplete((v, t) -> {
//            map.put("isSuccess", false);
//        }).exceptionally(exception -> {
//            System.out.println("捕获异常： " + exception);
//            return false;
//        });
//
//        CompletableFuture.allOf(purchase, purchase2, purchase3, purchase4)
//                .thenRun(() -> System.out.println("完成！！！"))
//                .join();


        return (boolean)map.get("isSuccess") ? Result.ok("购买成功") : Result.error("购买失败");
    }



    //并发抢购end------------------------------------------------------


    @GetMapping("/report/order")
    @ApiOperation("测试异步回调")
    public Result Report(){

        long start = System.currentTimeMillis();
//        Map<String, Object> map = orderReport();
        Map<String, Object> map = new HashMap<>();
        CompletableFuture<String> todayOrderCountFuture = orderService.todayOrderCount();
        CompletableFuture<String> todayTurnoverFuture = orderService.todayTurnover();
        CompletableFuture<String> totalTurnoverFuture = orderService.totalTurnover();

        System.out.println("任务开始");

        todayOrderCountFuture.whenComplete((v, t) -> {
            map.put("todayOrderCountFuture", v);
            System.out.println(t);
        });

        todayTurnoverFuture.whenComplete((v, t) -> {
            map.put("todayTurnoverFuture", v);
        });

        System.out.println("任务进行中");

        totalTurnoverFuture.whenComplete((v, t) -> {
            map.put("totalTurnoverFuture", v);
        });

        CompletableFuture.allOf(todayOrderCountFuture, todayTurnoverFuture, totalTurnoverFuture)
                .thenRun(() -> System.out.println("完成！！！"))
                .join();


        System.out.println("耗时： " + (System.currentTimeMillis() - start));
        return Result.ok(map);
    }

    public Map<String, Object> orderReport(){
        Map<String, Object> map = new HashMap<>();
        CompletableFuture<String> todayOrderCountFuture = orderService.todayOrderCount();
        CompletableFuture<String> todayTurnoverFuture = orderService.todayTurnover();
        CompletableFuture<String> totalTurnoverFuture = orderService.totalTurnover();

        System.out.println("任务开始");

        todayOrderCountFuture.whenComplete((v, t) -> {
            map.put("todayOrderCountFuture", v);
        });

        todayTurnoverFuture.whenComplete((v, t) -> {
            map.put("todayTurnoverFuture", v);
        });

        System.out.println("任务进行中");

        totalTurnoverFuture.whenComplete((v, t) -> {
            map.put("totalTurnoverFuture", v);
        });

        CompletableFuture.allOf(todayOrderCountFuture, todayTurnoverFuture, totalTurnoverFuture)
                .thenRun(() -> System.out.println("完成！！！"))
                .join();

        return map;
    }



    @GetMapping
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ApiOperation("纯属测试")
    public Result<String> sayHello() {
        System.out.println("111");
        return Result.ok("20200701晚上");
    }
}
