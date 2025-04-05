package com.example.backend.controller;

import com.example.backend.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParamsController {
    @RequestMapping(value = "/getTest1",method = RequestMethod.GET)
    public String getTest1(){
        return "GET请求";
    }


    @RequestMapping(value = "/getTest2",method = RequestMethod.GET)
    //http://localhost:8080/getTest2?nickname=Cecilia&phone=123
    //传递参数时，如果参数名和形参名一致，可以直接接收
    public String getTest2(String nickname,String phone){
        System.out.println("nickname:"+nickname);
        System.out.println("phone:"+phone);
        return "GET请求";
    }

    @RequestMapping(value = "/getTest3",method = RequestMethod.GET)
    //http://localhost:8080/getTest3?nickname=Cecilia

    /***
     * 传递参数时，如果参数名和形参名不一致，可以使用@RequestParam注解
     * 同时，如果使用@RequestParam注解，参数是必传的，如果不传会报错
     * 如果参数不是必传的，可以设置required=false
     *
     */
    public String getTest3(@RequestParam(value = "nickname",required = false) String name){
        System.out.println("nickname:"+name);
        return "GET请求";
    }
    @RequestMapping(value = "/postTest1",method = RequestMethod.POST)
    //http://localhost:8080/postTest1
    public String postTest1(){
        return "POST请求";
    }
    @RequestMapping(value = "/postTest2",method = RequestMethod.POST)
    public String postTest2(String username,String password){
        System.out.println("username:"+username);
        System.out.println("password:"+password);
        return "POST请求2";
    }
    @RequestMapping(value = "/postTest3",method = RequestMethod.POST)
    public String postTest3(User user){
        System.out.println(user);
        return "POST请求3";
    }
    @RequestMapping(value = "/postTest4",method = RequestMethod.POST)
    public String postTest4(@RequestBody User user){
        //接受json格式的参数，需要使用@RequestBody注解
        System.out.println(user);
        return "POST请求4";
    }
    @GetMapping("/test/**")
    //与"/test/*"的区别是，*只能匹配一层路径，**可以匹配多层路径
    //http://localhost:8080/test/1/2/3
    public String test(){
        return "通配符请求";
    }

}
