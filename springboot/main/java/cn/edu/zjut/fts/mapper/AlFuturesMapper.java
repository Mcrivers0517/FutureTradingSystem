package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.AlFuture;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Time;

@Mapper
public interface AlFuturesMapper extends BaseMapper<AlFuture> {

    @Select("SELECT price FROM al_futures LIMIT #{al_last_read_position_id}, 1")
    Integer selectAlPriceByLastReadPositionId(@Param("al_last_read_position_id") int al_last_read_position_id);

    @Select("SELECT date FROM al_futures LIMIT #{al_last_read_position_id}, 1")
    Long selectAldateByLastReadPositionId(@Param("al_last_read_position_id") int al_last_read_position_id);

    @Select("SELECT time FROM al_futures LIMIT #{al_last_read_position_id}, 1")
    Time selectAltimeByLastReadPositionId(@Param("al_last_read_position_id") int al_last_read_position_id);
    @Select("SELECT * FROM aluminum_data WHERE id = #{id}")
    AlFuture selectAlAllAttributesById(@Param("id") int id);
}
