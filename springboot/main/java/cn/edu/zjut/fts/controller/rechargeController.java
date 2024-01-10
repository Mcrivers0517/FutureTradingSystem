package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.*;

import cn.edu.zjut.fts.mapper.UserMapper;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("充值模块")
@RestController
@CrossOrigin
public class rechargeController {
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/recharge")
    public rechargeResult rechargeresult(@RequestBody rechargeRequest request) {
        System.out.println(request);
        rechargeResult result = new rechargeResult(true);
        userMapper.recharge(request.getUserid(),request.getRechargeAmount());
        return result;
    }
}
