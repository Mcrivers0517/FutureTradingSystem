package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.Futures;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface FuturesMapper extends BaseMapper<Futures>
{
    @Select("SELECT * FROM ${tablename} WHERE id = #{currentRow}")
    Futures selectAllByFutureAndId(@Param("tablename") String tablename, @Param("currentRow") int currentRow);

    @Select("select tablename from futures")
    List<String> selectAllTableName();

    @Update("update futures set DailyOpenPrice = #{dailyOpenPrice} where FutureID = #{futureId}")
    boolean updateDailyOpenPrice(@Param("dailyOpenPrice") double dailyOpenPrice, @Param("futureId") int futureId);

    @Update("update futures set DailyHighestPrice = #{dailyHighestPrice}, DailyLowestPrice = #{dailyLowestPrice} where FutureID = #{futureId}")
    boolean updateDailyPrice(@Param("dailyHighestPrice") double dailyHighestPrice, @Param("dailyLowestPrice") double dailyLowestPrice, @Param("futureId") int futureId);

    @Select("select DailyOpenPrice from futures where FutureID = #{futureId}")
    double selectDailyOpenPriceByFutureId(@Param("futureId") int futureId);
    @Select("select DailyHighestPrice from futures where FutureID = #{futureId}")
    double selectDailyHighestPriceByFutureId(@Param("futureId") int futureId);
    @Select("select DailyLowestPrice from futures where FutureID = #{futureId}")
    double selectDailyLowestPriceByFutureId(@Param("futureId") int futureId);
}
