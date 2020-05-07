package com.dt87.mapper;

import com.dt87.entity.MiaoshaOrder;
import com.dt87.entity.OrderInfo;
import com.dt87.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MiaoshaGoodsMapper {

    public List<GoodsVo> queryGoodslist();

    public GoodsVo queryGoodsByGoodsId(@Param("goodsId") long goodsId);

    public int reduceCount(@Param("goods") GoodsVo goods);

    public int inserOrderInfo(@Param("orderInfo") OrderInfo orderInfo);

    public int insertMiaoshaOrder(@Param("miaoshaOrder") MiaoshaOrder miaoshaOrder);
}
