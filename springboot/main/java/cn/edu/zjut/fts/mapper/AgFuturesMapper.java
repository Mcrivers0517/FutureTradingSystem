package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.AgFuture;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Time;

@Mapper
public interface AgFuturesMapper extends BaseMapper<AgFuture> {

    @Select("SELECT price FROM ag_futures LIMIT #{ag_last_read_position_id}, 1")
    Integer selectAgPriceByLastReadPositionId(@Param("ag_last_read_position_id") int ag_last_read_position_id);

    @Select("SELECT date FROM ag_futures LIMIT #{ag_last_read_position_id}, 1")
    Long selectAgdateByLastReadPositionId(@Param("ag_last_read_position_id") int ag_last_read_position_id);

    @Select("SELECT time FROM ag_futures LIMIT #{ag_last_read_position_id}, 1")
    Time selectAgtimeByLastReadPositionId(@Param("ag_last_read_position_id") int ag_last_read_position_id);
    @Select("SELECT * FROM sliver_data WHERE id = #{id}")
    AgFuture selectAgAllAttributesById(@Param("id") int id);
}
