package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.User;
import cn.edu.zjut.fts.mapper.UserMapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("注册模块")
@CrossOrigin
@RestController
public class RegisterController
{
    @Autowired
    private UserMapper userMapper;

//查询所有用户
//    @GetMapping("/user")
//    public  List query(){
//
//        List<User> list = userMapper.selectall();
//        System.out.println(list);
//        return list;
//    }

    @PostMapping("/register")
    public RegisterResponse insert(@RequestBody User Request)
    {
        try
        {
            userMapper.insert(Request);
            return new RegisterResponse(true);
        }
        catch (Exception e)
        {
            return new RegisterResponse(false);
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class RegisterResponse
    {
        @JsonProperty("isSuccess")
        private boolean isSuccess;
    }
}

