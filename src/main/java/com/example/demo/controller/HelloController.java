package com.example.demo.controller;
import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    //http://localhost:8080/hello
    //http://localhost:8080/hello?nickname=Cecilia&phone=123
    @RequestMapping (value = "/hello",method = RequestMethod.GET)
    //等价于 @GetMapping("/hello")
    public String hello(String nickname,String phone) {
        System.out.println(phone);
        return "Hello World!"+nickname;

    }
    //默认情况下，@Restcontroller注解将返回对象转换为json格式
    @RequestMapping("/user")
    public User getUser() {
         User user = new User();
            user.setUsername("Cecilia");
            user.setPassword("123456");
            return user;
    }
}
