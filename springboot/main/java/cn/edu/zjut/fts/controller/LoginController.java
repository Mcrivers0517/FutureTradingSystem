package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.User;

import cn.edu.zjut.fts.mapper.UserMapper;

import com.fasterxml.jackson.annotation.JsonProperty;
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
        String password = request.getPassword();
        User selectUser = userMapper.selectByUsername(request);
        Boolean isSuccess = false;
        System.out.println("selectUser:" + selectUser);

        if (selectUser != null && password.equals(selectUser.getPassword()))
        {
            isSuccess = true;
        }

        return new LoginResponse(isSuccess);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class LoginResponse
    {
        @JsonProperty("isSuccess")
        private boolean isSuccess;
    }

}

