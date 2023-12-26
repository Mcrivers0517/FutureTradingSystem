package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.a_Future;
import cn.edu.zjut.fts.entity.ag_Future;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Time;
import java.util.List;

@Mapper
public interface ag_futuresMapper extends BaseMapper<ag_Future> {

    @Select("SELECT price FROM ag_futures LIMIT #{ag_last_read_position_id}, 1")
    Integer selectag_PriceByLastReadPositionId(@Param("ag_last_read_position_id") int ag_last_read_position_id);

    @Select("SELECT date FROM ag_futures LIMIT #{ag_last_read_position_id}, 1")
    Long selectag_dateByLastReadPositionId(@Param("ag_last_read_position_id") int ag_last_read_position_id);

    @Select("SELECT time FROM ag_futures LIMIT #{ag_last_read_position_id}, 1")
    Time selectag_timeByLastReadPositionId(@Param("ag_last_read_position_id") int ag_last_read_position_id);
    @Select("SELECT * FROM ag_futures LIMIT #{ag_last_read_position_id}, 1")
    ag_Future selectag_AllAttributesByLastReadPositionId(@Param("ag_last_read_position_id") int ag_last_read_position_id);
}
