package cn.edu.zjut.fts.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("delegate")
public class DelegateEntity
{
    private int delegateId;
    private int futureId;
    private int userId;
    private String attribute;
    private String status;
    private int amount;
    private double delegatePrice;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private String delegateTime;
    private String deliveryDate;

}