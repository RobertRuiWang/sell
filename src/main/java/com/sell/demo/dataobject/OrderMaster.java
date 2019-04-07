package com.sell.demo.dataobject;

import com.sell.demo.enums.OrderStatusEnum;
import com.sell.demo.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class OrderMaster {
    //订单id
    @Id
    private String orderId;
    //买家名字
    private String buyerName;
    //买家手机号码
    private String buyerPhone;
    //买家住址
    private String buyerAddress;
    //买架微信Openid
    private String buyerOpenid;
    //订单总金额
    private BigDecimal orderAmount;
    //订单状态,默认为0新订单
    private Integer orderStatus= OrderStatusEnum.NEW.getCode();
    //支付状态，默认为0未支付
    private Integer payStatus= PayStatusEnum.WAIT.getCode();
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
}
