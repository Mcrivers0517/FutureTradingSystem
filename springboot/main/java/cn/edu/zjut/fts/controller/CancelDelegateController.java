package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.Delegate;
import cn.edu.zjut.fts.mapper.*;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    public String cancelOrder(@RequestBody CancelDelegateRequest request)
    {
        int delegateId = request.getDelegateId();
        String status = delegateMapper.selectStatus(delegateId);
        if ("已委".equals(status))
        {
            delegateMapper.updateStatus(delegateId);
            return "撤销订单成功";
        }
        else
        {
            return "订单已成，不能撤回";
        }
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class CancelDelegateRequest
{
    int delegateId;
}
