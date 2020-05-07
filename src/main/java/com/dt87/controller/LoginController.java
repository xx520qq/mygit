package com.dt87.controller;

import com.dt87.result.CodeMsg;
import com.dt87.result.Result;
import com.dt87.service.impl.MiaoshaUserServiceImpl;
import com.dt87.util.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private MiaoshaUserServiceImpl miaoshaUserService;

    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    //登录
    @RequestMapping("/do_login")
    @ResponseBody
    public Result<CodeMsg> login(long mobile, String password, HttpServletResponse response){
        //判断密码是否为空
        if(StringUtils.isEmpty(password)){
            return Result.error(CodeMsg.PASSWORD_ISNULL);
        }
        //判断手机号格式是否正确
        if(!ValidatorUtil.isMobile(""+mobile)){
            return Result.error(CodeMsg.MOBLE_ERROR);
        }
        CodeMsg msg =  this.miaoshaUserService.login(mobile,password,response);
        if(msg.getCode()==0){
            return Result.success(msg);
        }
        return Result.error(msg);

    }



}
