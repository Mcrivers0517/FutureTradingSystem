package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.Delegate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DelegateMapper extends BaseMapper<Delegate>{

    @Select("select * from delegate where status = '已委'")
    List<Delegate> selectCurrentDelegate();

    @Select("select * from delegate where status = '已成'")
    List<Delegate> selectHistoricalDelegate();

    @Insert("insert into delegate(userid, futureid, attribute, status, amount, delegatePrice, delegateTime) " +
            "values(#{userid}, #{futureid}, #{attribute}, #{status}, #{amount}, #{delegatePrice}, #{delegateTime})")
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






}
