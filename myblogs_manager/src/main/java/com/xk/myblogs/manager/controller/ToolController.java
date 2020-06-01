package com.xk.myblogs.manager.controller;



import com.xk.myblogs.manager.vo.Result;
import com.xk.myblogs.common.utils.Md5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiongkai
 * @date 2020年01月09日 14时08分08秒
 **/
@RestController
@RequestMapping("tool")
@Api(tags = "ToolController", description = "祖安工具")
public class ToolController {

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
}
