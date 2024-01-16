package cn.edu.zjut.fts.service;

import cn.edu.zjut.fts.entity.FuturesEntity;
import cn.edu.zjut.fts.mapper.FuturesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuturesServiceImpl implements FuturesService
{

    private final FuturesMapper futuresMapper;

    @Autowired
    public FuturesServiceImpl(FuturesMapper futuresMapper)
    {
        this.futuresMapper = futuresMapper;
    }

    public FuturesEntity getFuturesById(String tablename, int currentRow)
    {
        // 调用FuturesMapper的selectAllByFutureAndId方法查询期货信息
        return futuresMapper.selectAllByFutureAndId(tablename, currentRow);
    }

    public List<String> getAllTableNames()
    {
        // 调用FuturesMapper的selectAllTableName方法查询所有期货表的表名
        return futuresMapper.selectAllTableName();
    }

    public boolean updateDailyOpenPrice(double dailyOpenPrice, int futureId)
    {
        // 调用FuturesMapper的updateDailyOpenPrice方法更新期货的日开盘价
        return futuresMapper.updateDailyOpenPrice(dailyOpenPrice, futureId);
    }

    public boolean updateDailyPrice(double dailyHighestPrice, double dailyLowestPrice, int futureId)
    {
        // 调用FuturesMapper的updateDailyPrice方法更新期货的日最高价和日最低价
        return futuresMapper.updateDailyPrice(dailyHighestPrice, dailyLowestPrice, futureId);
    }

    public boolean updateCurrentPrice(double currentPrice, int futureId)
    {
        // 调用FuturesMapper的updateCurrentPrice方法更新期货的当前价格
        return futuresMapper.updateCurrentPrice(currentPrice, futureId);
    }

    public double getDailyOpenPriceByFutureId(int futureId)
    {
        // 调用FuturesMapper的selectDailyOpenPriceByFutureId方法查询期货的日开盘价
        return futuresMapper.selectDailyOpenPriceByFutureId(futureId);
    }

    public double getDailyHighestPriceByFutureId(int futureId)
    {
        // 调用FuturesMapper的selectDailyHighestPriceByFutureId方法查询期货的日最高价
        return futuresMapper.selectDailyHighestPriceByFutureId(futureId);
    }

    public double getDailyLowestPriceByFutureId(int futureId)
    {
        // 调用FuturesMapper的selectDailyLowestPriceByFutureId方法查询期货的日最低价
        return futuresMapper.selectDailyLowestPriceByFutureId(futureId);
    }
}
