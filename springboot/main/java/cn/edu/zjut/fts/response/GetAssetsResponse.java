package cn.edu.zjut.fts.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAssetsResponse
{
    private String username;
    private double deposit;
    private double currentCapital;
    private double totalProfitLoss;
    private double totalProfitLossRatio;
}

