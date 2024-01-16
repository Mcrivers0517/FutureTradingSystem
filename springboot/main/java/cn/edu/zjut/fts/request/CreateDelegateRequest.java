package cn.edu.zjut.fts.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDelegateRequest
{
    String dateTimeString;
    int futureId;
    int userId;
    int amount;
    double delegatePrice;
    String attribute;
}
