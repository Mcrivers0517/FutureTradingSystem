package cn.edu.zjut.fts.service;

import cn.edu.zjut.fts.entity.DelegateEntity;
import cn.edu.zjut.fts.mapper.DelegateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DelegateServiceImpl implements DelegateService
{

    private final DelegateMapper delegateMapper;

    @Autowired
    public DelegateServiceImpl(DelegateMapper delegateMapper)
    {
        this.delegateMapper = delegateMapper;
    }

    public List<DelegateEntity> getCurrentDelegateById(int userId, int futureId)
    {
        return delegateMapper.selectCurrentDelegateById(userId, futureId);
    }

    public List<DelegateEntity> getHistoricalDelegateById(int userId, int futureId)
    {
        return delegateMapper.selectHistoricalDelegateById(userId, futureId);
    }

    public List<DelegateEntity> getAllCurrentDelegateById(int userId)
    {
        return delegateMapper.selectAllCurrentDelegateById(userId);
    }

    public List<DelegateEntity> getAllHistoricalDelegateById(int userId)
    {
        return delegateMapper.selectAllHistoricalDelegateById(userId);
    }

    public boolean addDelegate(DelegateEntity delegateEntity)
    {
        return delegateMapper.insertDelegate(delegateEntity) > 0;
    }

    public String getDelegateStatus(int delegateId)
    {
        return delegateMapper.selectStatus(delegateId);
    }

    public boolean updateDelegateStatusToCancelled(int delegateId)
    {
        return delegateMapper.updateStatus(delegateId);
    }

    public List<DelegateEntity> getDelegatesByFuture(int futureId)
    {
        return delegateMapper.selectDelegatesByFutureAndUser(futureId);
    }

    public boolean updateDelegateStatusToDone(int delegateId)
    {
        return delegateMapper.updateDelegateStatusToDone(delegateId);
    }

    public boolean updateDelegateDeliveryDate(int delegateId, String deliveryDate)
    {
        return delegateMapper.updateDelegateDeliveryDate(delegateId, deliveryDate);
    }

    public int getDelegateIdByTime(String delegateTime)
    {
        return delegateMapper.selectDelegateIdByTime(delegateTime);
    }
}
