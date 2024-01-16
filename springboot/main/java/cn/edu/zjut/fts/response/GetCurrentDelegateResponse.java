package cn.edu.zjut.fts.response;

import cn.edu.zjut.fts.entity.Delegate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCurrentDelegateResponse
{
    List<Delegate> response;
}
