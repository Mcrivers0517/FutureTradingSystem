package cn.edu.zjut.fts.entity;

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
@TableName("transaction")

public class Transaction {
    private String id;
    private String username;
    private String bs;
    private int num;
    private int price;
    private int premium;
    private int pro;
    private Date time;
}
