package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.*;

import cn.edu.zjut.fts.mapper.UserMapper;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("充值模块")
@RestController
@CrossOrigin
public class RechargeController
{
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/recharge")
    public RechargeResult rechargeresult(@RequestBody RechargeRequest request) {
        System.out.println(request);
        RechargeResult result = new RechargeResult(true);
        userMapper.recharge(request.getUserid(),request.getRechargeAmount());
        return result;
    }
}
