package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.CancelDelegateRequest;
import cn.edu.zjut.fts.entity.CancelDelegateResult;
import cn.edu.zjut.fts.mapper.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "撤销委托订单模块")
@CrossOrigin
@RestController
public class CancelDelegateController
{

    @Autowired
    private DelegateMapper delegateMapper;

    @PostMapping("/cancelOrder")
    public CancelDelegateResult cancelOrder(@RequestBody CancelDelegateRequest request)
    {
        int delegateId = request.getDelegateId();
        CancelDelegateResult result =new CancelDelegateResult();
        System.out.println(delegateId);
        String status = delegateMapper.selectStatus(delegateId);
        System.out.println(status);
        if ("已委".equals(status))
        {
            delegateMapper.updateStatus(delegateId);
            result.setResult("撤销订单成功");
            return result;
        }
        else if (status==null){
            result.setResult("订单不存在");
            return result;
        }
        else
        {
            result.setResult("订单已成，不能撤回");
            return result;
        }
    }
}

