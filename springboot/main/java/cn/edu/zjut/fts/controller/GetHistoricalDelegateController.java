package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.request.GetHistoricalDelegateRequest;
import cn.edu.zjut.fts.response.GetHistoricalDelegateResponse;
import cn.edu.zjut.fts.service.DelegateService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "获取当前订单模块")
@CrossOrigin
@RestController
public class GetHistoricalDelegateController
{
    @Autowired
    private DelegateService delegateService;

    @PostMapping("/getHistoricalDelegate")
    public GetHistoricalDelegateResponse selectHistoricalDelegate(@RequestBody GetHistoricalDelegateRequest request)
    {
        System.out.println(request);
        if (request.getFutureId() == -1)
        {
            return new GetHistoricalDelegateResponse(delegateService.getAllHistoricalDelegateById(request.getUserId()));
        }
        else
        {
            return new GetHistoricalDelegateResponse(delegateService.getHistoricalDelegateById(request.getUserId(), request.getFutureId()));
        }
    }
}
