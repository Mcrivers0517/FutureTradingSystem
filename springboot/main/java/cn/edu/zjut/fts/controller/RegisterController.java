package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.RegisterRequest;
import cn.edu.zjut.fts.mapper.UserMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "撤销委托订单模块")
@CrossOrigin
@RestController
public class RegisterController
{
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/register")
    public boolean insert(@RequestBody RegisterRequest request)
    {

        System.out.println(request);
        String username = request.getUsername();
        String password = request.getPassword();

        // 直接保存用户信息到数据库
        int result = userMapper.insertUser(username, password);
        if (result > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

