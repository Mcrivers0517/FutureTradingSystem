package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User>
{
    //查询所有用户

    @Select("select * from user")
    List<User> selectall();

    //插入操作，用于注册实现
    @Insert("insert into user (username, password) values (#{username},#{password})")
    int insertUser(@Param("username") String username, @Param("password") String password);

    @Select("SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END AS username_exists\n" + "FROM User\n" + "WHERE username = #{username}")
    Integer selectIfExists(User user);

    @Select("select username from user where userid = #{userId}")
    String selectUsernameByUserId(@Param("userId") int userId);


    //查询操作，用于登录逻辑实现
    @Select("SELECT * FROM user WHERE username = #{username} ")
    User selectByUsername(User user);

    @Update("UPDATE user SET avatarUrl = #{avatarUrl} WHERE username = #{username}")
    int updateAvatarUrlByUsername(@Param("username") String username, @Param("avatarUrl") String avatarUrl);

    @Select("SELECT avatarUrl FROM user WHERE username = #{username}")
    String getAvatarUrlByUsername(@Param("username") String username);

    @Select("SELECT Deposit FROM user WHERE userid = #{userId}")
    double getDeposit(@Param("userId") int userId);

    @Select("SELECT InitialCapital FROM user WHERE userid = #{userId}")
    double getInitialCapital(@Param("userId") int userId);

    @Update("UPDATE user SET Deposit =Deposit + #{ProfitLoss} WHERE userid = #{UserID}")
    int updateDepositByUserID(@Param("UserID") int UserID, @Param("ProfitLoss") double ProfitLoss);

    @Update("UPDATE user SET InitialCapital =InitialCapital + #{ProfitLoss} WHERE userid = #{UserID}")
    int updateInitialCapitalByUserID(@Param("UserID") int UserID, @Param("ProfitLoss") double ProfitLoss);

    @Update("UPDATE user SET Deposit = Deposit + #{rechargeAmount} WHERE userid = #{userId}")
    int recharge(@Param("userId") int userId, @Param("rechargeAmount") double rechargeAmount);
}
