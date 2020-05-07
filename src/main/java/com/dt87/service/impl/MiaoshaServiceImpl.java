package com.dt87.service.impl;

import com.dt87.entity.MiaoshaUser;
import com.dt87.entity.OrderInfo;
import com.dt87.service.MiaoshaService;
import com.dt87.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MiaoshaServiceImpl implements MiaoshaService {

    @Autowired
    private MiaoshaGoodsServiceImpl miaoshaGoodsServiceImpl;

    @Autowired
    private MiaoshaOrderServiceImpl miaoshaOrderServiceImpl;

    @Override
    @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
        //1，减少库存
        int i = this.miaoshaGoodsServiceImpl.reduceCount(goods);
        //2,生成订单详情和秒杀订单
        OrderInfo orderInfo = this.miaoshaGoodsServiceImpl.insertOrder(user,goods);
        return orderInfo;
    }
}
