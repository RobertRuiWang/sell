package com.sell.demo.exception;

import com.sell.demo.enums.ResultEnum;
import com.sell.demo.vo.ResultVO;

public class SellException extends RuntimeException{
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }
    public SellException(Integer code,String message){
        super(message);
        this.code=code;
    }

}
