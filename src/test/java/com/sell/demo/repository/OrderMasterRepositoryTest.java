package com.sell.demo.repository;

import com.sell.demo.dataobject.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository repository;
    private final String OPENID="38";
    @Test
    public void saveTest(){
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("张豪杰");
        orderMaster.setBuyerPhone("18817279378");
        orderMaster.setBuyerAddress("上海大学");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(2.3));
        repository.save(orderMaster);
    }
     @Test
     public void findByBuyerOpenId(){
         PageRequest request=PageRequest.of(0,3);
         Page<OrderMaster> result=repository.findByBuyerOpenid(OPENID,request);
         System.out.println(result.getTotalElements());
     }

}