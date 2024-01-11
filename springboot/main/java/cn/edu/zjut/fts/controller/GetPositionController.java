package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.GetPositionRequest;
import cn.edu.zjut.fts.entity.Position;
import cn.edu.zjut.fts.mapper.PositionMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "获取历史订单模块")
@CrossOrigin
@RestController
public class GetPositionController
{
    @Autowired
    private PositionMapper positionMapper;

    @PostMapping("/getPosition")
    public List<Position> getPosition(@RequestBody GetPositionRequest request)
    {
        return positionMapper.getPositionByUserId(request.getUserId());
    }
}
