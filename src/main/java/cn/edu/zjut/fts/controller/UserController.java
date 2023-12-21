package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.User;
import cn.edu.zjut.fts.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

//查询所有用户
    @GetMapping("/user")
    public  List query(){

        List<User> list = userMapper.selectall();
        System.out.println(list);
        return list;
    }
//插入用户信息用于实现注册
     @PostMapping("/user")
    public String insert(User user){
    int i = userMapper.insert(user);
    if(i>0){
        return "插入成功";
    }else{
        return "插入失败";
    }
}
}

