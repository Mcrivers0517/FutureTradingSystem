package cn.edu.zjut.fts.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("transaction")

public class Transaction
{
    private int transactionid;
    private int delegateid;
    private double serviceFee;
    private String transactionTime;
}
