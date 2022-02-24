package com.example.demo.learn.clone;

/**
 * @ClassName User
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/2/24 17:23
 * @Version 1.0
 */
public class User implements Cloneable{

    private String name;

    private String nickName;

    private int age;

    private Address address;

    public User(String name, String nickName, int age, Address address) {
        this.name = name;
        this.nickName = nickName;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    protected User clone() throws CloneNotSupportedException {
        User clone = (User)super.clone();
        Address address = clone.getAddress().clone(); // 不加这个就是浅克隆
        clone.setAddress(address);
        return clone;
    }
}
