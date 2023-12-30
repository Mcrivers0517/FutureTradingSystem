package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.Delegate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DelegateMapper extends BaseMapper<Delegate>{

    @Select("select * from delegatetable")
    public List<Delegate> selectall();

    @Insert("insert into DelegateTable(id, username, att, status,num, delegatePrice,delegateTime) " +
            "values(#{id}, #{username}, #{att}, #{status},#{num}, #{delegatePrice}, #{delegateTime})")
    int insertDelegate(Delegate delegate);

    @Select("select status from DelegateTable where id = #{id} and username = #{username} and delegateTime = #{delegateTime}")
    String selectstatus(Delegate delegate);

    @Update("update DelegateTable set status = '已撤' where id = #{id} and username = #{username} and delegateTime = #{delegateTime}")
    Boolean updatestatus(Delegate delegate);

    @Select("select * from DelegateTable where status = '已委' and futureName = #{futureName} and username = #{username}")
    List<Delegate> selectDelegatesToUpdate(@Param("futureName") String futureName, @Param("username") String username);


    @Update("update DelegateTable set status = '已成' where futureName = #{futureName} and username = #{username} and delegateTime = #{delegateTime}")
    Boolean updateDelegateStatusToDone(Delegate delegate);








}
