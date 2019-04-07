package com.sell.demo.repository;

import com.sell.demo.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;
    @Test
    public void findOneTest(){

         ProductCategory productCategory=repository.findById(2).get();
         System.out.println(productCategory.toString());

    }
    @Test
    @Transactional
    public void saveTest(){
        ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryType(4);
        productCategory.setCategoryName("男生最爱");
        repository.save(productCategory);
    }
    @Test
    public void findByCategoryTypeTest(){
        List<Integer> list= Arrays.asList(2,3);
        List<ProductCategory> result=repository.findByCategoryTypeIn(list);
        for (ProductCategory product:result
             ) {
            System.out.println(product.toString());
        }
    }

}