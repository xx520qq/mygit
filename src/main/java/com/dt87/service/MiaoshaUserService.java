package com.dt87.service;

import com.dt87.result.CodeMsg;

import javax.servlet.http.HttpServletResponse;


public interface MiaoshaUserService {


    public CodeMsg login(long mobile, String password, HttpServletResponse response);
}
