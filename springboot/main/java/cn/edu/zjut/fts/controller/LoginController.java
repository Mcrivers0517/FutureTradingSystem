package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.User;

import cn.edu.zjut.fts.mapper.UserMapper;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("登录模块")
@RestController
@CrossOrigin
public class LoginController
{

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody User request)
    {
        System.out.println(request);
        String q_password = request.getPassword();
        String message = "";
//        loginresult result = new loginresult();
        Boolean success = userMapper.selectByUsername(request);
        if (success == null)
        {
            success = false;
            message = "用户名不存在";
        }
        else
        {
            if (q_password.equals(userMapper.selectpasswordByUsername(request)))
            {
                message = "登录成功";
            }
            else
            {
                success = false;
                message = "密码错误";
            }
        }

        return new LoginResponse(success, message);
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class LoginResponse
    {
        private boolean success;
        private String message;
    }

}

