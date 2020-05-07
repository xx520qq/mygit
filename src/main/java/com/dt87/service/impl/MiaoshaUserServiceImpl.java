package com.dt87.service.impl;

import com.dt87.entity.MiaoshaUser;
import com.dt87.mapper.MiaoshaUserMapper;
import com.dt87.redis.MiaoshaUserKey;
import com.dt87.redis.RedisService;
import com.dt87.result.CodeMsg;
import com.dt87.service.MiaoshaUserService;
import com.dt87.util.MD5Util;
import com.dt87.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class MiaoshaUserServiceImpl implements MiaoshaUserService {

    public static final String COOKIE_TOOKEN_NAME="tooke";

    @Autowired
    private MiaoshaUserMapper miaoshaUserMapper;
    @Autowired
    private RedisService redisService;

    public CodeMsg login(long mobile, String password, HttpServletResponse response) {
        MiaoshaUser user = this.miaoshaUserMapper.loginByMoble(mobile);
        //判断手机号是否存在
        if (user==null){
            return CodeMsg.MOBLE_NOFOUND;
        }

        //判断密码是否正确
        String salt = user.getSalt();
        String formpassword = MD5Util.formPassToDBPass(password, salt);
        String dbpassword = user.getPassword();
        if (!formpassword.equals(dbpassword)){
            return CodeMsg.PASSWORD_EROOR;
        }

        //cookie里面保存一个tooken（Redis里面的key），必须保证唯一存在
        //value值是Redis的key，要保证唯一
        String token = UUIDUtil.uuid();
        addOrUpdateCookie(token,user,response);
        return CodeMsg.SUCCESS;
    }

    private void addOrUpdateCookie(String token, MiaoshaUser user, HttpServletResponse response) {
        Cookie cookie = new Cookie(COOKIE_TOOKEN_NAME, token);
        cookie.setMaxAge(MiaoshaUserKey.TOKEN_EXPIRE);
        cookie.setPath("/");
        response.addCookie(cookie);
        //把数据保存到Redis里面
        this.redisService.set(MiaoshaUserKey.token,token,user);
    }

    public MiaoshaUser getUserByToken(String token, HttpServletResponse response) {
        MiaoshaUser user = this.redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class);
        //刷新用户的登录时间
        this.addOrUpdateCookie(token,user,response);
        return user;
    }
}
