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
@TableName("delegatetable")
public class Delegate {
    private String id;           // 品种
    private String username;     // 持仓人
    private String att;          // 属性
    private boolean status;      // 状态
    private int num;             // 委托数量
    private int price;           // 委托价格
    private Date time;           // 委托时间
}
