package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.Delegate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DelegateMapper extends BaseMapper<Delegate>{

    @Select("select * from delegatetable")
    public List<Delegate> selectall();

    @Insert("insert into DelegateTable(id, username, att, status,num, D_Price,D_Time) " +
            "values(#{id}, #{username}, #{att}, #{status},#{num}, #{D_Price}, #{D_Time})")
    int insertDelegate(Delegate delegate);


}
