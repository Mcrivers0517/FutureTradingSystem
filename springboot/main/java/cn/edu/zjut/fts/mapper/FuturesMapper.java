package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.Futures;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface FuturesMapper extends BaseMapper<Futures>
{
    @Select("SELECT * FROM ${tablename} WHERE id = #{currentRow}")
    Futures selectAllByFutureAndId(@Param("tablename") String tablename, @Param("currentRow") int currentRow);

    @Select("select tablename from futures")
    List<String> selectAllTableName();
}
