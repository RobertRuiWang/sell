package com.sell.demo.service.impl;

import com.sell.demo.dataobject.ProductInfo;
import com.sell.demo.dto.CartDTO;
import com.sell.demo.enums.ProductStatusEnum;
import com.sell.demo.enums.ResultEnum;
import com.sell.demo.exception.SellException;
import com.sell.demo.repository.ProductInfoRepository;
import com.sell.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository repository;
    @Override
    public ProductInfo findOne(String productId) {

        return repository.findById(productId).get();

    }

    @Override
    public List<ProductInfo> findAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(org.springframework.data.domain.Pageable pageable) {
        return repository.findAll(pageable);
    }


    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO carDTO:cartDTOList
             ) {
            ProductInfo productInfo=repository.findById(carDTO.getProductId()).get();
            if (productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result=productInfo.getProductStock()+carDTO.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);

        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO:cartDTOList
             ) {
            ProductInfo productInfo=repository.getOne(cartDTO.getProductId());
            if (productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result=productInfo.getProductStock()-cartDTO.getProductQuantity();
            if (result<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            repository.save(productInfo);

        }
    }
}
