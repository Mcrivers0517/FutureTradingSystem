package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.request.LoginRequest;
import cn.edu.zjut.fts.response.LoginResponse;
import cn.edu.zjut.fts.entity.UserEntity;

import cn.edu.zjut.fts.service.UserServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("登录模块")
@RestController
@CrossOrigin
public class LoginController
{

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request)
    {
        boolean isSuccess = false;
        int userID = -1;

        String username = request.getUsername();
        String password = request.getPassword();

        UserEntity selectUserEntity = userServiceImpl.getUserByUsername(username);
        System.out.println("selectUser:" + selectUserEntity);

        if (selectUserEntity != null && password.equals(selectUserEntity.getPassword()))
        {
            isSuccess = true;
            userID = selectUserEntity.getUserId();
        }

        return new LoginResponse(isSuccess, userID);
    }
}

