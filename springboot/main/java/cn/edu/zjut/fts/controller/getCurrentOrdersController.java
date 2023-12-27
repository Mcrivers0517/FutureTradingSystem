package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.Position;
import cn.edu.zjut.fts.mapper.PositionMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags = "获取当前订单模块")
@CrossOrigin
@RestController
public class getCurrentOrdersController {

    @Autowired
    private PositionMapper positionMapper;

    @GetMapping("/getCurrentOrders")
    public List selectAll() {
        List<Position> list = positionMapper.selectall();
        //System.out.println(list);
        return list;
    }

}
