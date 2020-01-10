package com.xk.myblogs_manager.controller;


import com.xk.myblogs.client.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author xiongkai
 * @date 2020年01月09日 14时08分08秒
 **/
@RestController
@RequestMapping("tool")
public class ToolController {

    @GetMapping
    @RequestMapping("/toTranslate")
    public Result<String> toTransalte(){
        return Result.ok("我是带弟弟柯西");
    }

}
