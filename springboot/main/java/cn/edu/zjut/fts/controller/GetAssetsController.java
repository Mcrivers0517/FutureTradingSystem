package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.*;

import cn.edu.zjut.fts.mapper.PositionMapper;
import cn.edu.zjut.fts.mapper.UserMapper;

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
    private UserMapper usermapper;

    @Autowired
    private PositionMapper positionMapper;

    @PostMapping("/getAssets")
    public GetAssetsResponse login(@RequestBody GetAssetsRequest request)
    {
        System.out.println(request);
        //获取request属性
        int userId = request.getUserId();

        //获取保证金
        GetAssetsResponse result = new GetAssetsResponse();
        result.setDeposit(usermapper.getDeposit(userId));

        //获取userid的position属性
        double chushizichan = 0;
        double currentCapital = 0;
        double totalProfitLoss = 0;
        double totalProfitLossRatio = 0;
        List<Position> positionList = positionMapper.getPositionByUserId(userId);
        System.out.println("----------------------------" + positionList);
        for (Position position : positionList)
        {
            currentCapital += position.getCurrentPrice() * position.getAmount();
            totalProfitLoss += position.getProfitLoss();
            System.out.println("----------------------------" + position);
        }
        chushizichan = usermapper.getInitialCapital(userId);
        totalProfitLossRatio = totalProfitLoss / chushizichan * 100;
        //设置属性
        result.setCurrentCapital(currentCapital);
        result.setTotalProfitLoss(totalProfitLoss);
        result.setTotalProfitLossRatio(totalProfitLossRatio);
        System.out.println(result);
        return result;
    }
}
