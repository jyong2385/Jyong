package com.jyong.java.designpattern;


import com.jyong.java.entity.Product;

/**
 * @Auther: wangjunyong
 * @Date: 2021/2/20 14:12
 * @Description:
 */
public class Customer implements Runnable {

    private Product product;

    public Customer(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 10; i++) {
            product.getProduct();
        }

    }
}
