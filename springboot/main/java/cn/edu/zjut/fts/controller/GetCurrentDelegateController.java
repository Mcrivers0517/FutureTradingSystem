package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.Delegate;
import cn.edu.zjut.fts.mapper.DelegateMapper;
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

    @GetMapping("/getCurrentDelegate")
    public List<Delegate> selectCurrentDelegate()
    {
        return delegateMapper.selectCurrentDelegate();
    }
}
