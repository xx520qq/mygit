package com.dt87.controller;

import com.dt87.entity.MiaoshaOrder;
import com.dt87.entity.MiaoshaUser;
import com.dt87.entity.OrderInfo;
import com.dt87.redis.RedisService;
import com.dt87.service.impl.MiaoshaGoodsServiceImpl;
import com.dt87.service.impl.MiaoshaOrderServiceImpl;
import com.dt87.service.impl.MiaoshaServiceImpl;
import com.dt87.service.impl.MiaoshaUserServiceImpl;
import com.dt87.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {

    @Autowired
    private MiaoshaGoodsServiceImpl miaoshaGoodsServiceImpl;

    @Autowired
    private MiaoshaOrderServiceImpl miaoshaOrderServiceImpl;

    @Autowired
    private MiaoshaServiceImpl miaoshaServiceImpl;

    //商品列表页面
    @RequestMapping("/do_miaosha")
    public String toLogin(ModelMap map,MiaoshaUser user,long goodsId) {
        if (user == null) {
            return "login";
        }
        map.put("user", user);

        //检查是否有库存
        GoodsVo goods = this.miaoshaGoodsServiceImpl.queryGoodsByGoodsId(goodsId);
        if(goods.getStockCount()<=0){
                map.put("msg","该商品被抢光了");
                return "miaosha_error";
            }

        //检查该用户是否已经秒杀过
        MiaoshaOrder miaoshaOrder = this.miaoshaOrderServiceImpl.queryGoodsByUserIdAndGoodsId(user.getId(), goodsId);
            if(miaoshaOrder!=null){
                map.put("msg","您已经参加过秒杀");
                return "miaosha_error";
            }
        //减少秒杀库存,生成秒杀订单详情，生成秒杀订单
        OrderInfo orderInfo = this.miaoshaServiceImpl.miaosha(user, goods);
        map.put("orderInfo", orderInfo);
        map.put("goods", goods);

        return "order_detail";

    }
}
