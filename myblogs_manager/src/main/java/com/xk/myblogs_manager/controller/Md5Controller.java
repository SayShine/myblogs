package com.xk.myblogs_manager.controller;

import com.xk.myblogs.client.vo.Result;
import com.xk.myblogs_util.Md5Util;
import org.springframework.web.bind.annotation.*;

/**
 * @Author kex
 * @Create 2020/1/11 12:40
 * @Description
 */
@RestController
@RequestMapping("md5")
public class Md5Controller {
    @PostMapping
    @RequestMapping("/toEncode")
    public Result<String> toEncoding(@RequestParam String code){
        String result = Md5Util.getMD5(code);
        return Result.ok(result);
    }
}
