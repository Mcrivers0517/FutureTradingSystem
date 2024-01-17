package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.PositionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PositionMapper extends BaseMapper<PositionEntity>
{

    @Insert("INSERT INTO `position`(UserID, FutureID, Amount, EntryPrice, CurrentPrice, ProfitLoss, EntryDate, LastUpdated, ProfitLossRatio, costPrice, `attribute`) " + "VALUES(#{userId}, #{futureId}, #{amount}, #{entryPrice}, #{currentPrice}, #{profitLoss}, #{entryDate}, #{lastUpdated}, #{profitLossRatio}, #{costPrice}, #{attribute})")
    int insertPosition(PositionEntity positionEntity);

    @Update("UPDATE `position` " + "SET UserID = #{userId}, " + "FutureID = #{futureId}, " + "Amount = #{amount}, " + "EntryPrice = #{entryPrice}, " + "CurrentPrice = #{currentPrice}, " + "ProfitLoss = #{profitLoss}, " + "EntryDate = #{entryDate}, " + "LastUpdated = #{lastUpdated}, " + "ProfitLossRatio = #{profitLossRatio}, " + "costPrice = #{costPrice}, " + "`attribute` = #{attribute} " + "WHERE PositionID = #{positionId}")
    int updatePosition(PositionEntity positionEntity);

    @Select("select * from `position` where futureId = #{futureId} and userId = #{userId} and `attribute` = #{attribute}")
    PositionEntity selectByFutureIdAndAttribute(@Param("futureId") int currentIndex, @Param("userId") int userid, @Param("attribute") String attribute);

    @Select("select * from `position` where positionid = #{positionid}")
    PositionEntity getPositionById(@Param("positionid") int positionid);

    @Update("update `position` set amount = amount - #{sellAmount} where positionid = #{positionId}")
    boolean updateAmountPositionById(@Param("positionId") int positionid, @Param("sellAmount") int sellAmount);

    @Select("select * from `position` where userId = #{userId}")
    List<PositionEntity> getPositionByUserId(@Param("userId") int userId);

    @Select("select * from `position` where futureId = #{futureId}")
    List<PositionEntity> selectPositionsByFutureId(@Param("futureId") int currentIndex);
}
