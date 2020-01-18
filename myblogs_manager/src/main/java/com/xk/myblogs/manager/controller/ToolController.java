package com.xk.myblogs.manager.controller;



import com.xk.myblogs.manager.vo.Result;
import com.xk.myblogs.common.utils.Md5Util;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiongkai
 * @date 2020年01月09日 14时08分08秒
 **/
@RestController
@RequestMapping("tool")
public class ToolController {

    @GetMapping
    @RequestMapping("/toTranslate")
    public Result<String> toTransalte() {
        return Result.ok("我是带弟弟柯西");
    }

    @GetMapping
    @RequestMapping("/toEncode")
    public Result<String> toEncoding(@RequestParam String code) {
        String result = Md5Util.getMD5(code);
        return Result.ok(result);
    }
}
