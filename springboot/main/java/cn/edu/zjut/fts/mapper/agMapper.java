package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.ag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface agMapper extends BaseMapper<ag> {

    @Select("SELECT ag_last_position_id FROM ag_last_read_position")
    int getag_Last_Read_PositionId();
}
