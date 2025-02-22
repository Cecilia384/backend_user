package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
//用于操作用户表，MyBatisPlus会根据Mapper注解，动态实现UserMapper接口（shixianlei),动态代理技术
//Spring会自动扫描Mapper接口，将其实例化为Bean对象，存入Spring容器中
//Spring容器中的Bean对象，可以直接调用Mapper接口中的方法，实现对数据库的增删改查
//即SpringBoot会自动创建UserMapper接口实现类对应的实例
@Mapper
public interface UserMapper extends BaseMapper <User> {
    //作用：继承BaseMapper接口，实现对数据库的增删改查
    //BaseMapper接口中已经定义了增删改查的方法，不需要再写
    @Select("select * from user where id=#{id}")
    User selectById(int id);

    //查询用户及其所有的订单
    @Select("select * from t_user")
    @Results({//配置多表查询,MyBatisPlus会根据@Results注解，动态生成SQL语句
            @Result(column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password"),
            @Result(column = "birthday",property = "birthday"),
            @Result(column = "id",property = "orders",javaType = List.class,
                    many = @Many(select = "com.example.demo.mapper.OrderMapper.selectByUid"))
            //many表示一对多的关系，select表示关联查询的方法
    })

    List<User> selectAllUserAndOrders();

//    @Insert("insert into user values(#{id},#{username} ,#{password},#{birthday})")
//    int add(User user);
//
//    @Update("update user set username=#{username},password=#{password},birthday=#{birthday} where id=#{id}")
//    int update(User user);
//
//    @Delete("delete from user where id=#{id}")
//    int delete(int id);
//

//
//    @Select("select * from user ")//查询所有用户
//    public List<User> getAll();
}
