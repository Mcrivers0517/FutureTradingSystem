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
    private String BS;
    private int pos;
    private int c_Pro;
    private double r_Pro;
    private int f_Price;
    private int n_Price;
    private Date n_Time;
    private Date Delivery;
}