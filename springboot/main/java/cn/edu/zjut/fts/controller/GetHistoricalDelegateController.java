package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.Delegate;
import cn.edu.zjut.fts.entity.GetHistoricalDelegateRequest;
import cn.edu.zjut.fts.mapper.DelegateMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "获取当前订单模块")
@CrossOrigin
@RestController
public class GetHistoricalDelegateController
{
    @Autowired
    private DelegateMapper delegateMapper;

    @PostMapping("/getHistoricalDelegate")
    public List<Delegate> selectHistoricalDelegate(@RequestBody GetHistoricalDelegateRequest request)
    {
//        System.out.println(request);
        if (request.getFutureId() == -1)
        {
            return delegateMapper.selectAllHistoricalDelegateById(request.getUserId());
        }
        else
        {
            return delegateMapper.selectHistoricalDelegateById(request.getUserId(), request.getFutureId());
        }
    }
}
