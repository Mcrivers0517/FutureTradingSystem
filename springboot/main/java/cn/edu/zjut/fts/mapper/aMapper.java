package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.a;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface aMapper extends BaseMapper<a> {

    @Select("SELECT a_last_position_id FROM a_last_read_position")
    int geta_Last_Read_PositionId();

    @Update("UPDATE a_last_read_position SET a_last_position_id = a_last_position_id + 1")
    int updateALastReadPositionId();
}
