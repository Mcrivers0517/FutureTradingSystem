package cn.edu.zjut.fts.service;

import cn.edu.zjut.fts.entity.UserEntity;

public interface UserService
{

    int registerUser(String username, String password);

    String getUsernameByUserId(int userId);

    UserEntity getUserByUsername(String username);

    int updateAvatarUrlByUsername(String username, String avatarUrl);

    String getAvatarUrlByUsername(String username);

    double getDeposit(int userId);

    double getInitialCapital(int userId);

    int updateDepositByUserID(int userID, double profitLoss);

    int updateInitialCapitalByUserID(int userID, double profitLoss);

    int recharge(int userId, double rechargeAmount);
}
