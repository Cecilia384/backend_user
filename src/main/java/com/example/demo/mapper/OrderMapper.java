package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper  extends BaseMapper<Order> {
    @Select("select * from t_order where uid=#{uid}")
    List<Order> selectByUid(int uid);
    //查询所有的订单，同时查询订单的用户
    @Select("select * from t_order")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "order_time",property = "order_time"),
            @Result(column = "total",property = "total"),
            @Result(column = "uid",property = "uid"),
            @Result(column = "uid",property = "user",javaType = User.class,
                    one = @One(select = "com.example.demo.mapper.UserMapper.selectById"))
            //one表示一对一的关系
            //调用UserMapper接口中的selectById方法，根据uid查询用户
    })
    List<Order> selectAllOrdersAndUser();
}
