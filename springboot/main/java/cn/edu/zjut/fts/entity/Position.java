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
@TableName("positiontable")
public class Position
{

    private String futureName;
    private String username;
    private String bs;
    private int amount;
    private int floatingProfitAndLoss;
    private double profitLossRatio;
    @JsonProperty("openPrice")
    private int openPrice;
    private int currentPrice;
    private Date currentTime;
    private Date deliveryDate;


}