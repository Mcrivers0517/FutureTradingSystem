package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.Delegate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DelegateMapper extends BaseMapper<Delegate>{

    @Select("select * from delegatetable")
    public List<Delegate> selectall();

    @Insert("insert into DelegateTable(id, username, att, status,num, D_Price,D_time) " +
            "values(#{id}, #{username}, #{att}, #{status},#{num}, #{D_Price}, #{D_time})")
    int insertDelegate(Delegate delegate);

    @Select("select status from DelegateTable where id = #{id} and username = #{username} and D_time = #{D_time}")
    String selectstatus(Delegate delegate);

    @Update("update DelegateTable set status = '已撤' where id = #{id} and username = #{username} and D_time = #{D_time}")
    Boolean updatestatus(Delegate delegate);






}
