package cn.edu.zjut.fts.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("al_futures")
public class al_Future {
    private Long date;
    private Time time;
    private int price;
    private int Volume;
}
