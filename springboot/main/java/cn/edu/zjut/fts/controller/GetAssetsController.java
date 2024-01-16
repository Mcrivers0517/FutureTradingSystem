package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.*;

import cn.edu.zjut.fts.mapper.PositionMapper;
import cn.edu.zjut.fts.mapper.UserMapper;

import cn.edu.zjut.fts.request.GetAssetsRequest;
import cn.edu.zjut.fts.response.GetAssetsResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("获取钱包模块")
@RestController
@CrossOrigin
public class GetAssetsController
{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PositionMapper positionMapper;

    @PostMapping("/getAssets")
    public GetAssetsResponse login(@RequestBody GetAssetsRequest request)
    {
        //获取request属性
        int userId = request.getUserId();

        System.out.println(request);

        //获取保证金
        GetAssetsResponse response = new GetAssetsResponse();
        response.setDeposit(userMapper.getDeposit(userId));
        response.setUsername(userMapper.selectUsernameByUserId(userId));

        //获取userid的position属性
        double initialCapital;
        double currentCapital = 0;
        double totalProfitLoss = 0;
        double totalProfitLossRatio;
        List<Position> positionList = positionMapper.getPositionByUserId(userId);
        for (Position position : positionList)
        {
            currentCapital += position.getCurrentPrice() * position.getAmount();
            totalProfitLoss += position.getProfitLoss();
        }
        initialCapital = userMapper.getInitialCapital(userId);
        if (initialCapital != 0)
        {
            totalProfitLossRatio = totalProfitLoss / initialCapital * 100;
        }
        else
        {
            totalProfitLossRatio = 0;
        }
        //设置属性
        response.setCurrentCapital(currentCapital);
        response.setTotalProfitLoss(totalProfitLoss);
        response.setTotalProfitLossRatio(totalProfitLossRatio);
        return response;
    }
}
