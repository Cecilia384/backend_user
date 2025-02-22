package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_order")
public class Order {
    private int id;
    private String order_time;
    private double total;
    private int uid;
    @TableField(exist = false)
    private User user;
    public Order() {
    }
    public Order(String order_time, int total, int uid) {
        this.order_time = order_time;
        this.total = total;
        this.uid = uid;
    }
    //toString方法
    @Override
    public String toString() {//重写toString方法.作用：将对象转换为字符串
        //用法：System.out.println(对象名);
        //含义：打印对象时，会自动调用toString方法
        return "Order{" +
                "id=" + id +
                ", order_time='" + order_time + '\'' +
                ", total=" + total +
                ", user=" + user +
                '}';
    }
    //getter、setter方法
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getOrder_time() {return order_time;}
    public void setOrder_time(String order_time) {this.order_time = order_time;}
    public double getTotal() {return total;}
    public void setTotal(int total) {this.total = total;}
    public int getUid() {return uid;}
    public void setUid(int uid) {this.uid = uid;}
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}

}


