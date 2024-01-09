package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.*;

import cn.edu.zjut.fts.mapper.PositionMapper;
import cn.edu.zjut.fts.mapper.UserMapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api("获取钱包模块")
@RestController
@CrossOrigin
public class getAssetsController {

    @Autowired
    private UserMapper usermapper;

    @Autowired
    private PositionMapper positionMapper;
    @PostMapping("/getAssets")
    public getAssetsResult login(@RequestBody getAssetsRequest request) {
        System.out.println(request);
        //获取request属性
        int userid = request.getUserid();

        //获取保证金
        getAssetsResult result =new getAssetsResult();
        result.setDeposit(usermapper.getDeposit(userid));

        //获取userid的position属性
        double chushizichan =0;
        double InitialCapital=0;
        double totalProfitLoss=0;
        double totalProfitLossRatio=0;
        List<Position> positionList = positionMapper.selectByuserid(userid);
        for (Position position : positionList) {
            InitialCapital += position.getCurrentPrice()*position.getAmount();
            totalProfitLoss += position.getProfitLoss()*position.getAmount();
            System.out.println(position);
        }
        chushizichan =usermapper.getInitialCapital(userid);
        totalProfitLossRatio =totalProfitLoss/chushizichan*100;
        //设置属性
        result.setInitialCapital(InitialCapital);
        result.setTotalProfitLoss(totalProfitLoss);
        result.setTotalProfitLossRatio(totalProfitLossRatio);
        System.out.println(result);
        return  result;
    }
}
