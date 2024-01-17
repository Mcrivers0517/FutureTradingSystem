package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.*;

import cn.edu.zjut.fts.request.GetAssetsRequest;
import cn.edu.zjut.fts.response.GetAssetsResponse;
import cn.edu.zjut.fts.service.PositionServiceImpl;
import cn.edu.zjut.fts.service.UserServiceImpl;
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
    private UserServiceImpl userServiceImpl;

    @Autowired
    private PositionServiceImpl positionServiceImpl;

    @PostMapping("/getAssets")
    public GetAssetsResponse getAssets(@RequestBody GetAssetsRequest request)
    {
        //获取request属性
        int userId = request.getUserId();

        //获取保证金
        GetAssetsResponse response = new GetAssetsResponse();
        response.setDeposit(userServiceImpl.getDeposit(userId));
        response.setUsername(userServiceImpl.getUsernameByUserId(userId));

        //获取userid的position属性
        double initialCapital;
        double currentCapital = 0;
        double totalProfitLoss = 0;
        double totalProfitLossRatio;
        List<PositionEntity> positionEntityList = positionServiceImpl.getPositionByUserId(userId);
        for (PositionEntity positionEntity : positionEntityList)
        {
            currentCapital += positionEntity.getCurrentPrice() * positionEntity.getAmount();
            totalProfitLoss += positionEntity.getProfitLoss();
        }
        initialCapital = userServiceImpl.getInitialCapital(userId);
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
