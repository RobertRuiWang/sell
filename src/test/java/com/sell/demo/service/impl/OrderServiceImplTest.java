package com.sell.demo.service.impl;

import com.sell.demo.dataobject.OrderDetail;
import com.sell.demo.dto.OrderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID="38";
    @Test
    public void create() {
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName("张豪杰");
        orderDTO.setBuyerAddress("慕课网");
        orderDTO.setBuyerPhone("18817238908");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        //购物车
        List<OrderDetail> orderDetailList=new ArrayList<>();
        OrderDetail o1=new OrderDetail();
        o1.setProductId("123456");
        o1.setProductQuantity(5);
        orderDetailList.add(o1);
        orderDTO.setOrderDetailList(orderDetailList);
        orderService.create(orderDTO);

    }

    @Test
    public void findOne() {
        System.out.println(orderService.findOne("1545296977521969498").toString());
    }

    @Test
    public void findList() {
        PageRequest request=PageRequest.of(0,2);
        Page<OrderDTO> orderDTOPage=orderService.findList(BUYER_OPENID,request);
        List<OrderDTO> orderDTOList=orderDTOPage.getContent();
        for (OrderDTO orderDTO:orderDTOList
             ) {
            System.out.println(orderDTO.toString());
        }
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO=orderService.findOne("1545296977521969498");
        OrderDTO result=orderService.cancel(orderDTO);
        System.out.println(result.toString());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO=orderService.findOne("1545296977521969498");
        OrderDTO result=orderService.finish(orderDTO);
        System.out.println(result.toString());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO=orderService.findOne("1545296977521969498");
        OrderDTO result=orderService.paid(orderDTO);
        System.out.println(result.toString());
    }
}