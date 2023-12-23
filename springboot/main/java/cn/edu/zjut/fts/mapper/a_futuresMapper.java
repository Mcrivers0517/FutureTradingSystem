package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.a_Future;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface a_futuresMapper extends BaseMapper<a_Future> {

    @Select("SELECT price FROM a_futures LIMIT #{a_last_read_position_id}, 1")
    Integer selectPriceByLastReadPositionId(@Param("a_last_read_position_id") int a_last_read_position_id);

}
