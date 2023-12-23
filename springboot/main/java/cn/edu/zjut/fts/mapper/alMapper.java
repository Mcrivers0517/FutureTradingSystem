package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.al;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface alMapper extends BaseMapper<al> {

    @Select("SELECT al_last_position_id FROM al_last_read_position")
    int getal_Last_Read_PositionId();
}
