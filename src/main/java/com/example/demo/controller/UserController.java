package com.example.demo.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    //使用@Autowired注解，自动注入UserMapper对象
    @Autowired
    private UserMapper userMapper;
    //查询所有用户
    @GetMapping("/user/findAll")
   public List<User> find(){
        return userMapper.selectAllUserAndOrders();
    }
    //添加用户
    @PostMapping("/user")
    public String save(User user) {
       int i = userMapper.insert(user);//i表示受影响的行数
         if (i>0) {
              return "添加成功";
         } else {
              return "添加失败";
         }
    }
    //根据ID查询用户
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable int id) {
        User user = userMapper.selectById(id);
        return user;
    }
    //根据ID删除用户
    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable int id) {
        int i = userMapper.deleteById(id);
        if (i>0) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }
    //更新用户
    @PutMapping("/user")
    public String update(User user) {
        int i = userMapper.updateById(user);
        if (i>0) {
            return "更新成功";
        } else {
            return "更新失败";
        }
    }
    //条件查询
    @GetMapping("/user/find")
    public List<User> findCond(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username","Cecilia");
        return userMapper.selectList(queryWrapper);
    }
    //分页查询
    @GetMapping("/user/findByPage")
    public IPage findByPage(){//IPage表示分页查询的结果
        Page<User> page=new Page<>(0,2);
        IPage iPage = userMapper.selectPage(page, null);//null表示查询所有
        return iPage;
    }


//    @Operation(summary = "获取用户",description = "根据用户ID获取用户信息")
//    @GetMapping("/user/{id}")
//    public String getUserById(@PathVariable int id) {
//        //@PathVariable注解，获取路径中的参数
//        System.out.println("id:"+id);
//        return "根据ID获取用户信息";
//    }
//    @PostMapping("/user")
//    public String save(User user) {
//        return "添加用户";
//    }
//    @PutMapping("/user")
//    public  String update(User user) {
//        return "更新用户";
//    }
//    @DeleteMapping("/user/{id}")
//    public String delete(@PathVariable int id) {
//
//        System.out.println("id:"+id);
//        return "根据ID删除用户";
//    }
}
