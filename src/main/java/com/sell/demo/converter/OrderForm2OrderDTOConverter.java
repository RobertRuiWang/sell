package com.sell.demo.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sell.demo.dataobject.OrderDetail;
import com.sell.demo.dto.OrderDTO;
import com.sell.demo.enums.ResultEnum;
import com.sell.demo.exception.SellException;
import com.sell.demo.form.Orderform;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO convert(Orderform orderform){
        Gson gson=new Gson();
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName(orderform.getName());
        orderDTO.setBuyerPhone(orderform.getPhone());
        orderDTO.setBuyerAddress(orderform.getAddress());
        orderDTO.setBuyerOpenid(orderform.getOpenid());
        List<OrderDetail> orderDetailList=new ArrayList<>();
        try {
            orderDetailList=gson.fromJson(orderform.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
