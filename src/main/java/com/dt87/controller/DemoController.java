package com.dt87.controller;

import com.dt87.result.CodeMsg;
import com.dt87.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

    //json api 接口数据

    @RequestMapping("/hello")
    @ResponseBody
    public Result<CodeMsg> Hello(){
        return Result.success(CodeMsg.SUCCESS);
    }

    @RequestMapping("/helloeroor")
    @ResponseBody
    public Result<CodeMsg> HelloEroor(){
        return Result.error(CodeMsg.SERVER_ERROR);
    }
}
