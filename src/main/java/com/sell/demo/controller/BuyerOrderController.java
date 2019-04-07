package com.sell.demo.controller;

import com.sell.demo.converter.OrderForm2OrderDTOConverter;
import com.sell.demo.dto.OrderDTO;
import com.sell.demo.enums.ResultEnum;
import com.sell.demo.exception.SellException;
import com.sell.demo.form.Orderform;
import com.sell.demo.service.BuyerService;
import com.sell.demo.service.OrderService;
import com.sell.demo.utils.ResultVOUtil;
import com.sell.demo.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;
    @PostMapping("/create")
    //创建订单
    public ResultVO<Map<String,String>> create(@Valid Orderform orderform, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO= OrderForm2OrderDTOConverter.convert(orderform);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult=orderService.create(orderDTO);
        Map<String,String> map=new HashMap<>();
        map.put("orderId",createResult.getOrderId());
        return ResultVOUtil.success(map);
    }
    @GetMapping("/list")
    //订单列表
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page",defaultValue = "0") Integer page,
                                         @RequestParam(value = "size",defaultValue = "10")Integer size){
        if (StringUtils.isEmpty(openid)){
            log.error("[查询订单列表]openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request=PageRequest.of(page,size);
        Page<OrderDTO> orderDTOPage=orderService.findList(openid,request);
        return ResultVOUtil.success(orderDTOPage.getContent());

    }
    //订单详请
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,@RequestParam("orderId")String orderId){
        OrderDTO orderDTO=buyerService.findOrderOne(openid, orderId);
        return ResultVOUtil.success(orderDTO);

    }
    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openId,@RequestParam("orderId") String orderId){

        buyerService.cancelOrder(openId, orderId);
        //orderService.cancel(orderDTO);
        return ResultVOUtil.success();
    }
}
