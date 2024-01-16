package cn.edu.zjut.fts.service;

import cn.edu.zjut.fts.entity.Delegate;
import cn.edu.zjut.fts.mapper.DelegateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DelegateService
{

    private final DelegateMapper delegateMapper;

    @Autowired
    public DelegateService(DelegateMapper delegateMapper)
    {
        this.delegateMapper = delegateMapper;
    }

    public List<Delegate> getCurrentDelegateById(int userId, int futureId)
    {
        return delegateMapper.selectCurrentDelegateById(userId, futureId);
    }

    public List<Delegate> getHistoricalDelegateById(int userId, int futureId)
    {
        return delegateMapper.selectHistoricalDelegateById(userId, futureId);
    }

    public List<Delegate> getAllCurrentDelegateById(int userId)
    {
        return delegateMapper.selectAllCurrentDelegateById(userId);
    }

    public List<Delegate> getAllHistoricalDelegateById(int userId)
    {
        return delegateMapper.selectAllHistoricalDelegateById(userId);
    }

    public boolean addDelegate(Delegate delegate)
    {
        return delegateMapper.insertDelegate(delegate) > 0;
    }

    public String getDelegateStatus(int delegateId)
    {
        return delegateMapper.selectStatus(delegateId);
    }

    public boolean updateDelegateStatusToCancelled(int delegateId)
    {
        return delegateMapper.updateStatus(delegateId);
    }

    public List<Delegate> getDelegatesByFuture(int futureId)
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
