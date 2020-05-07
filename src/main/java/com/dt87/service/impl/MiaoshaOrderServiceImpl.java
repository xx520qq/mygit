package com.dt87.service.impl;

import com.dt87.entity.MiaoshaOrder;
import com.dt87.mapper.MiaoshaOrderMapper;
import com.dt87.service.MiaoshaOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiaoshaOrderServiceImpl implements MiaoshaOrderService {
    @Autowired
    private MiaoshaOrderMapper miaoshaOrderMapper;

    @Override
    public MiaoshaOrder queryGoodsByUserIdAndGoodsId(long userId, long goodsId) {
        return miaoshaOrderMapper.queryGoodsByUserIdAndGoodsId(userId,goodsId);
    }
}
