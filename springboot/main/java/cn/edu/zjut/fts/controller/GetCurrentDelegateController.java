package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.request.GetCurrentDelegateRequest;
import cn.edu.zjut.fts.response.GetCurrentDelegateResponse;
import cn.edu.zjut.fts.service.DelegateService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "获取当前订单模块")
@CrossOrigin
@RestController
public class GetCurrentDelegateController
{
    @Autowired
    private DelegateService delegateService;

    @PostMapping("/getCurrentDelegate")
    public GetCurrentDelegateResponse getCurrentDelegate(@RequestBody GetCurrentDelegateRequest request)
    {
        if (request.getFutureId() == -1)
        {
            return new GetCurrentDelegateResponse(delegateService.getAllCurrentDelegateById(request.getUserId()));
        }
        else
        {
            return new GetCurrentDelegateResponse(delegateService.getCurrentDelegateById(request.getUserId(), request.getFutureId()));
        }
    }
}
