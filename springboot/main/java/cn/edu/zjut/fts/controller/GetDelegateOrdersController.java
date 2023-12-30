package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.Delegate;
import cn.edu.zjut.fts.mapper.DelegateMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags = "获取委托订单模块")
@CrossOrigin
@RestController
public class GetDelegateOrdersController
{

    @Autowired
    private DelegateMapper DelegateMapper;

    @GetMapping("/getDelegateOrders")
    public List selectAll() {
        List<Delegate> list = DelegateMapper.selectall();
        //System.out.println(list);
        return list;
    }

}
