package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.request.RechargeRequest;
import cn.edu.zjut.fts.response.RechargeResponse;
import cn.edu.zjut.fts.service.UserServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("充值模块")
@RestController
@CrossOrigin
public class RechargeController
{
    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/recharge")
    public RechargeResponse recharge(@RequestBody RechargeRequest request)
    {
        System.out.println(request);
        if (userServiceImpl.recharge(request.getUserId(), request.getRechargeAmount()) == 1)
        {
            return new RechargeResponse(true);
        }
        else
        {
            return new RechargeResponse(false);
        }
    }
}
