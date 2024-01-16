package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.request.GetPositionRequest;
import cn.edu.zjut.fts.mapper.PositionMapper;
import cn.edu.zjut.fts.response.GetPositionResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "获取持仓")
@CrossOrigin
@RestController
public class GetPositionController
{
    @Autowired
    private PositionMapper positionMapper;

    @PostMapping("/getPosition")
    public GetPositionResponse getPosition(@RequestBody GetPositionRequest request)
    {
        return new GetPositionResponse(positionMapper.getPositionByUserId(request.getUserId()));
    }
}
