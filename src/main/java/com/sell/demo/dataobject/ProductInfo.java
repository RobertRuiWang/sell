package com.sell.demo.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;

@Entity
@Data
public class ProductInfo {
    @Id
    private String productId;
    //名字
    private String productName;
    //单价
    private BigDecimal productPrice;
    //库存
    private Integer productStock;
    //商品描述
    private String productDescription;
    //小图
    private String productIcon;
    //状态
    private Integer productStatus;
    //类目编号
    private Integer categeoryType;

}
