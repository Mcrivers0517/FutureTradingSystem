package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.request.CancelDelegateRequest;
import cn.edu.zjut.fts.response.CancelDelegateResponse;
import cn.edu.zjut.fts.service.DelegateService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "撤销委托订单模块")
@CrossOrigin
@RestController
public class CancelDelegateController
{

    @Autowired
    private DelegateService delegateService;

    @PostMapping("/cancelDelegate")
    public CancelDelegateResponse cancelOrder(@RequestBody CancelDelegateRequest request)
    {
        int delegateId = request.getDelegateId();
        String status = delegateService.getDelegateStatus(delegateId);

        if ("已委".equals(status))
        {
            delegateService.updateDelegateStatusToCancelled(delegateId);
            return new CancelDelegateResponse("撤销订单成功");
        }
        else if (status == null)
        {
            return new CancelDelegateResponse("订单不存在");
        }
        else
        {
            return new CancelDelegateResponse("订单已成，不能撤回");
        }
    }
}

