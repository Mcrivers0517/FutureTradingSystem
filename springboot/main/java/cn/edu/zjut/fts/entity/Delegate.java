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
@TableName("delegatetable")
public class Delegate {
    private String id;
    private String username;
    private String att;
    private boolean status;
    private int num;
    private int D_Price;
    private Date D_Time;

}