package com.sell.demo.service.impl;

import com.sell.demo.dataobject.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;
    @Test
    public void findOne() {
        ProductInfo productInfo=productService.findOne("123456");
        System.out.println(productInfo.toString());
    }

    @Test
    public void findAll() {
        List<ProductInfo> productInfoList=productService.findAll();
        for (ProductInfo productInfo:productInfoList
             ) {
            System.out.println(productInfo.toString());
        }
    }

    @Test
    public void findAll1() {
        PageRequest request=PageRequest.of(0,1);
        Page<ProductInfo> productInfoPage=productService.findAll(request);
        System.out.println(productInfoPage.getTotalElements());
    }

    @Test
    public void save() {
    }
}