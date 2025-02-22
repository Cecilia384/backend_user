package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

//@TableName("t_user")//指定表名
public class User {
    @TableId(type = IdType.AUTO)//设置id自增
    private int id;
    private String username;
    private String password;
    private String birthday;

    @TableField(exist = false)//表示该属性不是数据库表中的字段(MyBaitsPlus里de)
    private List<Order> orders;//表示用户和订单是一对多的关系
    public User() {

    }


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.birthday = birthday;

    }
    //toString方法
    @Override
    public String toString() {//重写toString方法.作用：将对象转换为字符串
        //用法：System.out.println(对象名);
        //含义：打印对象时，会自动调用toString方法
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getBirthday() {return birthday;}
    public void setBirthday(String birthday) {this.birthday = birthday;}

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
