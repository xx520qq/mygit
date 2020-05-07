package com.dt87.service;

import com.dt87.entity.MiaoshaUser;
import com.dt87.entity.OrderInfo;
import com.dt87.vo.GoodsVo;

public interface MiaoshaService {

    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods);


}
