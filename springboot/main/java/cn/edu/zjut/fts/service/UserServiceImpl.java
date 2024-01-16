package cn.edu.zjut.fts.service;

import cn.edu.zjut.fts.entity.UserEntity;
import cn.edu.zjut.fts.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService
{

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper)
    {
        this.userMapper = userMapper;
    }

    public int registerUser(String username, String password)
    {
        // 调用UserMapper的insertUser方法插入用户信息
        return userMapper.insertUser(username, password);
    }

    public String getUsernameByUserId(int userId)
    {
        // 调用UserMapper的selectUsernameByUserId方法查询用户名
        return userMapper.selectUsernameByUserId(userId);
    }

    public UserEntity getUserByUsername(String username)
    {
        // 调用UserMapper的selectByUsername方法查询用户信息
        return userMapper.selectByUsername(username);
    }

    public int updateAvatarUrlByUsername(String username, String avatarUrl)
    {
        // 调用UserMapper的updateAvatarUrlByUsername方法更新用户头像URL
        return userMapper.updateAvatarUrlByUsername(username, avatarUrl);
    }

    public String getAvatarUrlByUsername(String username)
    {
        // 调用UserMapper的getAvatarUrlByUsername方法查询用户头像URL
        return userMapper.getAvatarUrlByUsername(username);
    }

    public double getDeposit(int userId)
    {
        // 调用UserMapper的getDeposit方法查询用户存款金额
        return userMapper.getDeposit(userId);
    }

    public double getInitialCapital(int userId)
    {
        // 调用UserMapper的getInitialCapital方法查询用户初始资金
        return userMapper.getInitialCapital(userId);
    }

    public int updateDepositByUserID(int userID, double profitLoss)
    {
        // 调用UserMapper的updateDepositByUserID方法更新用户存款金额
        return userMapper.updateDepositByUserID(userID, profitLoss);
    }

    public int updateInitialCapitalByUserID(int userID, double profitLoss)
    {
        // 调用UserMapper的updateInitialCapitalByUserID方法更新用户初始资金
        return userMapper.updateInitialCapitalByUserID(userID, profitLoss);
    }

    public int recharge(int userId, double rechargeAmount)
    {
        // 调用UserMapper的recharge方法执行用户充值操作
        return userMapper.recharge(userId, rechargeAmount);
    }
}
