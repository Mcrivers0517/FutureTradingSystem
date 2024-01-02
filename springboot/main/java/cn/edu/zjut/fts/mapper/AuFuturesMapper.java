package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.AuFuture;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Time;

@Mapper
public interface AuFuturesMapper extends BaseMapper<AuFuture> {

    @Select("SELECT price FROM au_futures LIMIT #{a_last_read_position_id}, 1")
    Integer selectAuPriceByLastReadPositionId(@Param("a_last_read_position_id") int a_last_read_position_id);

    @Select("SELECT date FROM au_futures LIMIT #{a_last_read_position_id}, 1")
    Long selectAudateByLastReadPositionId(@Param("a_last_read_position_id") int a_last_read_position_id);

    @Select("SELECT time FROM au_futures LIMIT #{a_last_read_position_id}, 1")
    Time selectAutimeByLastReadPositionId(@Param("a_last_read_position_id") int a_last_read_position_id);
    @Select("SELECT * FROM gold_data WHERE id = #{id}")
    AuFuture selectAuAllAttributesById(@Param("id") int id);




}
