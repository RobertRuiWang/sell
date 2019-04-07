package com.sell.demo.repository;

import com.sell.demo.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
@Autowired
    private OrderDetailRepository repository;
@Test
    public void saveTest(){
    OrderDetail orderDetail=new OrderDetail();
    orderDetail.setDetailId("1234567");
    orderDetail.setOrderId("11111115");
    orderDetail.setProductIcon("http:xxxx.jpg");
    orderDetail.setProductName("浏阳蒸菜");
    orderDetail.setProductPrice(new BigDecimal(1.2));
    orderDetail.setProductQuantity(23);
    orderDetail.setProductId("12123123");
    repository.save(orderDetail);

}
@Test
    public void findByOrderId(){
    List<OrderDetail> orderDetailsList=repository.findByOrderId("123456");
    Assert.assertNotEquals(0,orderDetailsList.size());
}
}