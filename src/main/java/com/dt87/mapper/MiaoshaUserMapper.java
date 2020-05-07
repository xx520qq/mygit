package com.dt87.mapper;

import com.dt87.entity.MiaoshaUser;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface MiaoshaUserMapper {
    public MiaoshaUser loginByMoble(@Param("mobile") long moble);

}
