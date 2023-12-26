package cn.edu.zjut.fts.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Future {
    private Long date;
    private Time time;
    private int price;
    private int Volume;
}
