package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.a_Future;
import cn.edu.zjut.fts.entity.al_Future;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Time;
import java.util.List;

@Mapper
public interface al_futuresMapper extends BaseMapper<al_Future> {

    @Select("SELECT price FROM al_futures LIMIT #{al_last_read_position_id}, 1")
    Integer selectal_PriceByLastReadPositionId(@Param("al_last_read_position_id") int al_last_read_position_id);

    @Select("SELECT date FROM al_futures LIMIT #{al_last_read_position_id}, 1")
    Long selectal_dateByLastReadPositionId(@Param("al_last_read_position_id") int al_last_read_position_id);

    @Select("SELECT time FROM al_futures LIMIT #{al_last_read_position_id}, 1")
    Time selectal_timeByLastReadPositionId(@Param("al_last_read_position_id") int al_last_read_position_id);
    @Select("SELECT * FROM al_futures LIMIT #{al_last_read_position_id}, 1")
    al_Future selectal_AllAttributesByLastReadPositionId(@Param("al_last_read_position_id") int al_last_read_position_id);
}
