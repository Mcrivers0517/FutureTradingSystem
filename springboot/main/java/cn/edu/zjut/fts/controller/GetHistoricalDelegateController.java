package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.request.GetHistoricalDelegateRequest;
import cn.edu.zjut.fts.mapper.DelegateMapper;
import cn.edu.zjut.fts.response.GetHistoricalDelegateResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "获取当前订单模块")
@CrossOrigin
@RestController
public class GetHistoricalDelegateController
{
    @Autowired
    private DelegateMapper delegateMapper;

    @PostMapping("/getHistoricalDelegate")
    public GetHistoricalDelegateResponse selectHistoricalDelegate(@RequestBody GetHistoricalDelegateRequest request)
    {
        System.out.println(request);
        if (request.getFutureId() == -1)
        {
            return new GetHistoricalDelegateResponse(delegateMapper.selectAllHistoricalDelegateById(request.getUserId()));
        }
        else
        {
            return new GetHistoricalDelegateResponse(delegateMapper.selectHistoricalDelegateById(request.getUserId(), request.getFutureId()));
        }
    }
}
