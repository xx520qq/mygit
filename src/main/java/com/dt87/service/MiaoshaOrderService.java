package com.dt87.service;

import com.dt87.entity.MiaoshaOrder;


public interface MiaoshaOrderService {

    public MiaoshaOrder queryGoodsByUserIdAndGoodsId(long userId,long goodsId);

}
