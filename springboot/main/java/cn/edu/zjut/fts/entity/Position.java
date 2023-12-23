package cn.edu.zjut.fts.entity;

import com.baomidou.mybatisplus.annotation.*;
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

    private String id;
    private String username;
    private String bs;
    private int pos;
    private int C_Pro;
    private double R_Pro;
    private int F_Price;
    private int N_Price;
    private Date N_Time;
    private Date Delivery;


}