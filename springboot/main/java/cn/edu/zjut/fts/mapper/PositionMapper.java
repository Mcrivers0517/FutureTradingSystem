package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.Position;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PositionMapper extends BaseMapper<Position>
{

    @Select("select * from position")
    public List<Position> selectall();

    @Insert("INSERT INTO position(UserID, FutureID, Amount, EntryPrice, CurrentPrice, ProfitLoss, EntryDate, LastUpdated, ProfitLossRatio, costPrice) " +
            "VALUES(#{userId}, #{futureId}, #{amount}, #{entryPrice}, #{currentPrice}, #{profitLoss}, #{entryDate}, #{lastUpdated}, #{profitLossRatio}, #{costPrice})")
    int insertPosition(Position position);

    @Update("UPDATE position " +
            "SET UserID = #{userId}, " +
            "FutureID = #{futureId}, " +
            "Amount = #{amount}, " +
            "EntryPrice = #{entryPrice}, " +
            "CurrentPrice = #{currentPrice}, " +
            "ProfitLoss = #{profitLoss}, " +
            "EntryDate = #{entryDate}, " +
            "LastUpdated = #{lastUpdated}, " +
            "ProfitLossRatio = #{profitLossRatio}, " +
            "DeliveryDate = #{deliveryDate}, " +
            "costPrice = #{costPrice} " +
            "attribute = #{attribute} " +
            "WHERE PositionID = #{positionId}")
    int updatePosition(Position position);


    @Select("select * from position where futureId = #{futureId} and userId = #{userId} and attribute = #{attribute}")
    Position selectByFutureNameAndAttribute(@Param("futureId") int currentIndex, @Param("userid") int userid, @Param("attribute") String attribute);

    @Select("select * from position where positionid = #{positionid}")
    Position getPositionById(@Param("positionid") int positionid);

    @Update("update position set amount = amount - #{sellAmount} where positionid = #{positionId}")
    boolean updateAmountPositionById(@Param("positionId") int positionid,@Param("sellAmount") int sellAmount);
}
