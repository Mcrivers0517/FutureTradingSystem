package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.Delegate;
import cn.edu.zjut.fts.request.GetCurrentDelegateRequest;
import cn.edu.zjut.fts.mapper.DelegateMapper;
import cn.edu.zjut.fts.response.GetCurrentDelegateResponse;
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
    private DelegateMapper delegateMapper;

    @PostMapping("/getCurrentDelegate")
    public GetCurrentDelegateResponse getCurrentDelegate(@RequestBody GetCurrentDelegateRequest request)
    {
        if (request.getFutureId() == -1)
        {
            return new GetCurrentDelegateResponse(delegateMapper.selectAllCurrentDelegateById(request.getUserId()));
        }
        else
        {
            return new GetCurrentDelegateResponse(delegateMapper.selectCurrentDelegateById(request.getUserId(), request.getFutureId()));
        }
    }
}
