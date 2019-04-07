package com.sell.demo.service.impl;

import com.sell.demo.dto.OrderDTO;
import com.sell.demo.enums.ResultEnum;
import com.sell.demo.exception.SellException;
import com.sell.demo.service.BuyerService;
import com.sell.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;
    @Override
    public OrderDTO findOrderOne(String openId, String orderId) {
        return  checkOrderOwner(openId,orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openId, String orderId) {
        OrderDTO orderDTO=checkOrderOwner(openId, orderId);
        if (orderDTO==null){
            log.error("[取消订单]查不到该订单，orderId={}",orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openId,String orderId){
        OrderDTO orderDTO=orderService.findOne(orderId);
        if (orderDTO==null){
            return null;
        }
        //判断是否是自己的订单
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openId)){
            log.error("[查询订单]订单的openid不一致，openid={},orderId={}");
            throw  new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }

        return orderDTO;
    }
}
