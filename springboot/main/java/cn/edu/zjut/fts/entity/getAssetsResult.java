package cn.edu.zjut.fts.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class getAssetsResult
{
    private double Deposit;
    private double InitialCapital;
    private double totalProfitLoss;
    private double totalProfitLossRatio;
}

