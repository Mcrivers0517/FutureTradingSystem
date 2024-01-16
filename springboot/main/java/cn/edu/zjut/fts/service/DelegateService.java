package cn.edu.zjut.fts.service;

import cn.edu.zjut.fts.entity.DelegateEntity;

import java.util.List;

public interface DelegateService
{

    List<DelegateEntity> getCurrentDelegateById(int userId, int futureId);

    List<DelegateEntity> getHistoricalDelegateById(int userId, int futureId);

    List<DelegateEntity> getAllCurrentDelegateById(int userId);

    List<DelegateEntity> getAllHistoricalDelegateById(int userId);

    boolean addDelegate(DelegateEntity delegateEntity);

    String getDelegateStatus(int delegateId);

    boolean updateDelegateStatusToCancelled(int delegateId);

    List<DelegateEntity> getDelegatesByFuture(int futureId);

    boolean updateDelegateStatusToDone(int delegateId);

    boolean updateDelegateDeliveryDate(int delegateId, String deliveryDate);

    int getDelegateIdByTime(String delegateTime);
}
