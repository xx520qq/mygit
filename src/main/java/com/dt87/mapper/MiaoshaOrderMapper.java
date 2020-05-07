package com.dt87.mapper;

import com.dt87.entity.MiaoshaOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MiaoshaOrderMapper {

    public MiaoshaOrder queryGoodsByUserIdAndGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);

}
