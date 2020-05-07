package com.dt87.service.impl;

import com.dt87.entity.MiaoshaOrder;
import com.dt87.entity.MiaoshaUser;
import com.dt87.entity.OrderInfo;
import com.dt87.mapper.MiaoshaGoodsMapper;
import com.dt87.service.MiaoshaGoodsService;
import com.dt87.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
public class MiaoshaGoodsServiceImpl implements MiaoshaGoodsService {

    @Autowired
    private MiaoshaGoodsMapper miaoshaGoodsMapper;

    @Override
    public List<GoodsVo> queryGoodsList() {
        return miaoshaGoodsMapper.queryGoodslist();
    }

    @Override
    public GoodsVo queryGoodsByGoodsId(long goodsId) {
        return miaoshaGoodsMapper.queryGoodsByGoodsId(goodsId);
    }

    @Override
    public int reduceCount(GoodsVo goods) {
        return miaoshaGoodsMapper.reduceCount(goods);
    }

    @Override
    @Transactional
    public OrderInfo insertOrder(MiaoshaUser user, GoodsVo goods) {
        //1，订单表插入数据
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
        orderInfo.setGoodsChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());

        int i1 =this.miaoshaGoodsMapper.inserOrderInfo(orderInfo);
        //2，秒杀订单插入数据
        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setGoodsId(goods.getId());
        miaoshaOrder.setOrderId(orderInfo.getId());
        miaoshaOrder.setUserId(user.getId());
        int i2 = this.miaoshaGoodsMapper.insertMiaoshaOrder(miaoshaOrder);
        if(i1>0&&i2>0){
            return orderInfo;
        }
        return null;
    }
}
