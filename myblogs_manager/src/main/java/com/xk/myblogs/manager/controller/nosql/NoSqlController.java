package com.xk.myblogs.manager.controller.nosql;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.xk.myblogs.client.entity.nosql.User;
import com.xk.myblogs.manager.vo.Result;
import com.xk.myblogs.service.NosqlService;
import com.xk.myblogs.service.nosqldao.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: tian
 * @date: 2020/7/12 21:20
 */
@RestController
@RequestMapping("nosql")
@Api(tags = "NoSqlController", description = "nosql控制层")
public class NoSqlController {
    @Autowired
    private NosqlService nosqlService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    @ApiOperation("新增用户信息")
    public Result savaUser(@RequestBody String jsonString){
        boolean flag = true;
        try {
            User user = JSONObject.parseObject(jsonString, User.class);
            nosqlService.savaMdList(user);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }

        //根据json字符串进行博客内容新增或更改
        return flag?Result.ok("新增信息成功"):Result.error("输入有误，新增信息失败");
    }

    @GetMapping("/user/{id}")
    @ApiOperation("根据用户名id获取用户")
    public Result<User> getUserById(@PathVariable Long id){
        User user = nosqlService.getUserById(id);
        if(user == null){
            return Result.error("用户不存在");
        }
        return Result.ok(user);
    }

    @DeleteMapping("/user/{id}")
    @ApiOperation("删除用户")
    public Result deleteUserById(@PathVariable Long id){
        DeleteResult result = nosqlService.deleteUserById(id);
        return Result.ok(result);
    }

    /**
     *
     * @param userName  用户名称
     * @param note      备注
     * @param skip      跳过用户个数
     * @param limit     限制返回用户个数
     * @return
     */
    @GetMapping("/nosql")
    @ApiOperation("nosql查找用户")
    public Result<List<User>> findUser(@RequestParam String userName, @RequestParam String note,
                                       @RequestParam Integer skip, @RequestParam Integer limit){
        List<User> userList = nosqlService.findUser(userName, note, skip, limit);
        return Result.ok(userList);
    }

    @PutMapping("/nosql")
    @ApiOperation("更新用户部分属性")
    public Result updateUser(@RequestParam Integer id, @RequestParam String userName,
                             @RequestParam String note){
        UpdateResult result = nosqlService.updateUser(id, userName, note);
        return Result.ok(result);
    }

    @GetMapping("/byName/{userName}")
    @ApiOperation("使用jpa根据用户名查询")
    public Result<List<User>> findByUserName(@PathVariable String userName){
        List<User> userList = userRepository.findByUserNameLike(userName);
        return Result.ok(userList);
    }

    @GetMapping("byIdAndName/{id}/{userName}")
    @ApiOperation("使用不太规范的jpa查询/自定义方法查询")
    public Result<User> findByUserNameAndId(@PathVariable Long id, @PathVariable String userName){
//        User user = userRepository.findByMyMethod(id, userName);
        User user = userRepository.findUserByIdOrUserName(id, userName);
        return Result.ok(user);
    }
}
