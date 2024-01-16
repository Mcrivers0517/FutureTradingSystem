package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.request.RegisterRequest;
import cn.edu.zjut.fts.response.RegisterResponse;
import cn.edu.zjut.fts.service.UserServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "撤销委托订单模块")
@CrossOrigin
@RestController
public class RegisterController
{
    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request)
    {

        System.out.println(request);
        String username = request.getUsername();
        String password = request.getPassword();

        // 直接保存用户信息到数据库
        if (userServiceImpl.registerUser(username, password) > 0)
        {
            return new RegisterResponse(true);
        }
        else
        {
            return new RegisterResponse(false);
        }
    }
}

