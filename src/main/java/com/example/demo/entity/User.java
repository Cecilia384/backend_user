package com.example.demo.entity;

public class User {
    private String username;
    private String password;
    private String birthday;
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
}
