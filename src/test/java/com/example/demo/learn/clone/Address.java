package com.example.demo.learn.clone;

/**
 * @ClassName Address
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/2/24 17:24
 * @Version 1.0
 */
public class Address implements Cloneable{

    private String city;

    private String province;

    public Address(String city, String province) {
        this.city = city;
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    protected Address clone() throws CloneNotSupportedException {
        return (Address)super.clone();
    }
}
