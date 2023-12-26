package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.ag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface agMapper extends BaseMapper<ag> {

    @Select("SELECT ag_last_position_id FROM ag_last_read_position")
    int getag_Last_Read_PositionId();

    @Update("UPDATE ag_last_read_position SET ag_last_position_id = ag_last_position_id + 1")
    int updateAgLastReadPositionId();
}
