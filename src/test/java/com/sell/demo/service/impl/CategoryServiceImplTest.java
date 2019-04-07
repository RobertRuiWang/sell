package com.sell.demo.service.impl;

import com.sell.demo.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;



@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {
   @Autowired
   private  CategoryServiceImpl categoryService;

    @Test
    public void findOne() {
        System.out.println(categoryService.findOne(1));
    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategoryList;
        productCategoryList=categoryService.findAll();
        for (ProductCategory productCategory: productCategoryList
             ) {
            System.out.println(productCategory.toString());
        }
    }

    @Test
    public void findByCategoryTypeIn() {
    }

    @Test
    public void save() {
    }
}