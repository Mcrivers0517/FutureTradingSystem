package com.FTS.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
public class Position {
    private String id;             // 品种
    private String username;       // 持仓人
    private String bs;             // 买或卖
    private int pos;               // 当前持仓量
    private int c_Pro;              // 浮动盈亏
    private double r_Pro;           // 盈亏比率
    private int f_Price;            // 开仓价格
    private int n_Price;            // 现在价格
    private Date n_Time;            // 成交时间
}