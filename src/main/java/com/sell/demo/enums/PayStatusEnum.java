package com.sell.demo.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功"),
    ;
    private Integer code;
    private String status;

    PayStatusEnum(Integer code, String status) {
        this.code = code;
        this.status = status;
    }
}
