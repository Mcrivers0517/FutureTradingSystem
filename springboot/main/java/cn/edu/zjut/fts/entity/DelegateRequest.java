package cn.edu.zjut.fts.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DelegateRequest
{
    String dateTimeString;
    int futureId;
    int userId;
    int amount;
    double delegatePrice;
    String attribute;
}
