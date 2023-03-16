package com.jyong.java.entity;



import java.io.Serializable;

public class Person implements Serializable {
    String id ;
    String name ;
    String sex ;
    String birthday ;
    String address ;
    String amount ;
    String education ;
    String job ;
    String phone ;
    String creditcard ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreditcard() {
        return creditcard;
    }

    public void setCreditcard(String creditcard) {
        this.creditcard = creditcard;
    }

    public Person(){}
    public Person(String id , String name , String sex, String birthday, String address, String amount, String education, String job, String phone, String creditcard){
        this.id=id;
        this.name=name;
        this.sex=sex;
        this.birthday=birthday;
        this.address=address;
        this.amount=amount;
        this.education=education;
        this.job=job;
        this.phone=phone;
        this.creditcard=creditcard;
    }





}
