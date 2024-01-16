package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.request.LoginRequest;
import cn.edu.zjut.fts.response.LoginResponse;
import cn.edu.zjut.fts.entity.User;

import cn.edu.zjut.fts.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("登录模块")
@RestController
@CrossOrigin
public class LoginController
{

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request)
    {
        boolean isSuccess = false;
        int userID = -1;

        String username = request.getUsername();
        String password = request.getPassword();

        User selectUser = userService.getUserByUsername(username);
        System.out.println("selectUser:" + selectUser);

        if (selectUser != null && password.equals(selectUser.getPassword()))
        {
            isSuccess = true;
            userID = selectUser.getUserId();
        }

        return new LoginResponse(isSuccess, userID);
    }
}

