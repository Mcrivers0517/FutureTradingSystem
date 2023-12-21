package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<User> {
    //查询所有用户

    @Select("select * from user")
    public List<User> selectall();

    @Insert("insert into user values (#{id},#{username},#{password},#{avatarUrl})")
    public int insert(User user);
}
