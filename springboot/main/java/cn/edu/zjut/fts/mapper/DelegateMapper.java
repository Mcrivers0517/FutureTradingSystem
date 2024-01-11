package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.Delegate;
import cn.edu.zjut.fts.entity.Position;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DelegateMapper extends BaseMapper<Delegate>
{

    @Select("SELECT * FROM delegate WHERE status = '已委' AND userId = #{userId} AND futureId = #{futureId}")
    List<Delegate> selectCurrentDelegateById(@Param("userId") int userId, @Param("futureId") int futureId);

    @Select("SELECT * FROM delegate WHERE status = '已成' AND userId = #{userId} AND futureId = #{futureId}")
    List<Delegate> selectHistoricalDelegateById(@Param("userId") int userId, @Param("futureId") int futureId);

    @Select("SELECT * FROM delegate WHERE status = '已委' AND userId = #{userId}")
    List<Delegate> selectAllCurrentDelegateById(@Param("userId") int userId);

    @Select("SELECT * FROM delegate WHERE status = '已成' AND userId = #{userId}")
    List<Delegate> selectAllHistoricalDelegateById(@Param("userId") int userId);

    @Select("select * from delegate where status = '已成'")
    List<Delegate> selectHistoricalDelegate();

    @Insert("insert into delegate(userid, futureid, attribute, status, amount, delegatePrice, delegateTime,deliveryDate) " + "values(#{userId}, #{futureId}, #{attribute}, #{status}, #{amount}, #{delegatePrice}, #{delegateTime},#{deliveryDate})")
    int insertDelegate(Delegate delegate);

    @Select("select status from delegate where delegateid = #{delegateid}")
    String selectStatus(@Param("delegateid") int delegateid);

    @Update("update delegate set status = '已撤' where delegateid = #{delegateid}")
    boolean updateStatus(@Param("delegateid") int delegateid);

    @Select("select * from delegate where status = '已委' and futureid = #{futureid}")
    List<Delegate> selectDelegatesByFutureAndUser(@Param("futureid") int currentIndex);

    @Update("update delegate set status = '已成' where delegateid = #{delegateid}")
    boolean updateDelegateStatusToDone(@Param("delegateid") int delegateid);

    @Update("update delegate set deliverydate = #{deliverydate} where delegateid = #{delegateid}")
    boolean updateDelegateDeliveryDate(@Param("delegateid") int delegateid, @Param("deliverydate") String deliverydate);

    @Select("select * from position where PositionID = #{PositionID}")
    Delete getPositionById(@Param("PositionID") int PositionID);

    @Select("select delegateid from delegate where delegateTime = #{delegateTime}")
    int selectDelegateIdByTime(@Param("delegateTime") String delegateTime);
}
