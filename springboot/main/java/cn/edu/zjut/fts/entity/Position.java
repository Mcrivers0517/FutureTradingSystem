package cn.edu.zjut.fts.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("position")
public class Position
{

    private int positionId;
    private int userId;
    private int futureId;
    private double amount;
    private double entryPrice;
    private double currentPrice;
    private double profitLoss;
    private double profitLossRatio;
    private double costPrice;
    private String entryDate;
    private String lastUpdate;
}