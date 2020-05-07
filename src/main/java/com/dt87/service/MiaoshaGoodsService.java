package com.dt87.service;

import com.dt87.entity.MiaoshaUser;
import com.dt87.entity.OrderInfo;
import com.dt87.vo.GoodsVo;

import java.util.List;

public interface MiaoshaGoodsService {

    public List<GoodsVo> queryGoodsList();


    public GoodsVo queryGoodsByGoodsId(long goodsId);

    public int reduceCount(GoodsVo goods);

    public OrderInfo insertOrder(MiaoshaUser user, GoodsVo goods);
}
