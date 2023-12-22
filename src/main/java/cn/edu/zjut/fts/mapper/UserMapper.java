package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<User> {
    //查询所有用户

    @Select("select * from user")
    public List<User> selectall();
    //插入操作，用于注册实现
    @Insert("insert into user values (#{id},#{username},#{password},#{avatarUrl})")
    public int insert(User user);

    //查询操作，用于登录逻辑实现
    @Select("SELECT * FROM user WHERE username = #{username}")
    User selectByUsername(@Param("username") String username);


}