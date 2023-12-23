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
@TableName("a_futures")
public class a_Future {
    private Date date;
    private Date time;
    private int price;
    private int Volume;
}
