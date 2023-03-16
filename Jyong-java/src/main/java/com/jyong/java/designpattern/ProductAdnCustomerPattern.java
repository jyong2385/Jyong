package com.jyong.java.designpattern;


import com.jyong.java.entity.Product;

/**
 * @Auther: wangjunyong
 * @Date: 2021/2/20 14:15
 * @Description:
 */
public class ProductAdnCustomerPattern {

    public static void main(String[] args) {
        Product p1 = new Product();
        Customer c1 = new Customer(p1);

        c1.run();


    }
}
