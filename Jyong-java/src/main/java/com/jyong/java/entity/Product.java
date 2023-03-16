package com.jyong.java.entity;

/**
 * @Auther: wangjunyong
 * @Date: 2021/2/20 13:55
 * @Description:
 */
public class Product {

    private String brand, name;
    boolean flag = false; //商品得状态,flag:true 有商品，false：无商品

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    //消费商品
    public synchronized void getProduct() {

        if (!flag) {
            //如果没有商品就等待
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //有商品，进行消费
        System.out.println("消费者消费了："+this.getBrand()+"---"+this.getName());
        //消费完之后，将flag置为false
        flag=false;
        //唤醒生产者进行生产商品
        notify();
    }

    //生产商品
    public synchronized void setProduct(){
        //如果有商品就等待
        if(flag){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.setBrand(brand);
        this.setName(name);
        //有商品就消费
        System.out.println("生产者生产了："+this.getBrand()+"---"+this.getName());
        //生产完将flag置为true
        flag=true;
        //将消费者唤醒
        notify();


    }

}
