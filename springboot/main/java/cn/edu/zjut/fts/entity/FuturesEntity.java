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
@TableName("futures")
public class FuturesEntity
{
    private double price;
    private int volume;
    private double dailyOpenPrice;
    private double dailyHighestPrice;
    private double dailyLowestPrice;
    private double dailyChange;
    private double dailyChangeRatio;
    private boolean transacted;
    private int transactedDelegateId;
}
