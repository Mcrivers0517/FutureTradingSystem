package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.request.GetPositionRequest;
import cn.edu.zjut.fts.response.GetPositionResponse;
import cn.edu.zjut.fts.service.PositionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "获取持仓")
@CrossOrigin
@RestController
public class GetPositionController
{
    @Autowired
    private PositionService positionService;

    @PostMapping("/getPosition")
    public GetPositionResponse getPosition(@RequestBody GetPositionRequest request)
    {
        return new GetPositionResponse(positionService.getPositionByUserId(request.getUserId()));
    }
}
