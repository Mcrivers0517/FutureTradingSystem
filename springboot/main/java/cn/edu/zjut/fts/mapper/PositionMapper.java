package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.Position;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PositionMapper extends BaseMapper<Position>
{

    @Select("select * from positiontable")
    public List<Position> selectall();

    @Insert("insert into positiontable(futureName, username, bs, amount, floatingProfitAndLoss, profitLossRatio, openPrice, currentPrice, currentTime, deliveryDate) " + "values(#{futureName}, #{username}, #{bs}, #{amount}, #{floatingProfitAndLoss}, #{profitLossRatio}, #{openPrice}, #{currentPrice}, #{currentTime}, #{deliveryDate})")
    int insertPosition(Position position);

    @Update("update positiontable set floatingProfitAndLoss = #{floatingProfitAndLoss}, profitLossRatio = #{profitLossRatio}, currentPrice = #{currentPrice}")
    int updatePosition(@Param("floatingProfitAndLoss") int floatingProfitAndLoss, @Param("profitLossRatio") double profitLossRatio, @Param("currentPrice") int currentPrice);

    @Select("select * from positiontable where futureName = #{futureName}")
    Position selectByFutureName(@Param("futureName") String futureName);
}
