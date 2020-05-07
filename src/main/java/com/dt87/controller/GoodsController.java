package com.dt87.controller;

import com.dt87.entity.MiaoshaUser;
import com.dt87.redis.RedisService;
import com.dt87.service.impl.MiaoshaGoodsServiceImpl;
import com.dt87.service.impl.MiaoshaUserServiceImpl;
import com.dt87.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private MiaoshaUserServiceImpl miaoshaUserServiceImpl;

    @Autowired
    private MiaoshaGoodsServiceImpl miaoshaGoodsServiceImpl;

    //商品列表页面
    @RequestMapping("/to_list")
    public String toLogin(ModelMap map,MiaoshaUser user){
        if(user==null){
            return "login";
        }
        //将用户信息传到前端
        map.put("user",user);
        List<GoodsVo> goodsList = this.miaoshaGoodsServiceImpl.queryGoodsList();
        map.put("goodsList",goodsList);
        return "goods_list";
    }

    //前往商品详情页面
    @RequestMapping("/to_detail/{goodsId}")
    public String detail(ModelMap map, MiaoshaUser user, @PathVariable("goodsId") long goodsId){
        if(user==null){
            return "login";
        }
        map.put("user",user);
        GoodsVo goods = this.miaoshaGoodsServiceImpl.queryGoodsByGoodsId(goodsId);
        map.put("goods",goods);

        //秒杀的状态
        long miaoshaStatus=0;//0 未开始 1 开始 2 秒杀结束
        //秒杀剩余的时间
        long remainSeconds=0;// 0 开始
        long start = goods.getStartDate().getTime();
        long end = goods.getEndDate().getTime();
        long now = System.currentTimeMillis(); //服务器当前的时间
        if(now<start){ //秒杀未开始
            miaoshaStatus=0;
            remainSeconds=(start-now)/1000;
        }else if(now > end){ //秒杀结束
            miaoshaStatus=2;
            remainSeconds=-1;
        }else{ //秒杀开始
            miaoshaStatus=1;
            remainSeconds=0;
        }
        map.put("miaoshaStatus",miaoshaStatus);
        map.put("remainSeconds",remainSeconds);

        return "goods_detail";
    }




}
