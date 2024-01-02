package cn.edu.zjut.fts.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Future
{
    private int price;
    private int Volume;
}
