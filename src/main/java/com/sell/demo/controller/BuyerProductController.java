package com.sell.demo.controller;

import com.sell.demo.dataobject.ProductCategory;
import com.sell.demo.dataobject.ProductInfo;
import com.sell.demo.service.ProductService;
import com.sell.demo.service.impl.CategoryServiceImpl;
import com.sell.demo.service.impl.ProductServiceImpl;
import com.sell.demo.utils.ResultVOUtil;
import com.sell.demo.vo.ProductInfoVo;
import com.sell.demo.vo.ProductVO;
import com.sell.demo.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private CategoryServiceImpl categoryService;
    //@GetMapping("/list")
    @GetMapping("/list")
    public ResultVO list(){
        //查询所有上架的商品
        List<ProductInfo> productInfoList=productService.findAll();
        //查询类目（一次性查询）
        //List<Integer> categoryTypeList=new ArrayList<>();
        //传统方法
        //for (ProductInfo productInfo:productInfoList){
            //categoryTypeList.add(productInfo.getCategeoryType());
       // }
        //精简方法
        List<Integer> categoryTypeList = productInfoList.stream().map(o -> o.getCategeoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList=categoryService.findByCategoryTypeIn(categoryTypeList);
        //数据拼装
        List<ProductVO> productVOList=new ArrayList<>();
        for (ProductCategory productCategory:productCategoryList){
            ProductVO productVO=new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());
            List<ProductInfoVo> productInfoVoList=new ArrayList<>();
            for (ProductInfo productInfo:productInfoList){
                if (productInfo.getCategeoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVo productInfoVo=new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVO.setFoods(productInfoVoList);
            productVOList.add(productVO);
        }
       return ResultVOUtil.success(productVOList);

    }
}
