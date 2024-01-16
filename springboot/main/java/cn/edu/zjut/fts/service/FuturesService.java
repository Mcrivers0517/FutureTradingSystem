package cn.edu.zjut.fts.service;

import cn.edu.zjut.fts.entity.FuturesEntity;

import java.util.List;

public interface FuturesService
{

    FuturesEntity getFuturesById(String tablename, int currentRow);

    List<String> getAllTableNames();

    boolean updateDailyOpenPrice(double dailyOpenPrice, int futureId);

    boolean updateDailyPrice(double dailyHighestPrice, double dailyLowestPrice, int futureId);

    boolean updateCurrentPrice(double currentPrice, int futureId);

    double getDailyOpenPriceByFutureId(int futureId);

    double getDailyHighestPriceByFutureId(int futureId);

    double getDailyLowestPriceByFutureId(int futureId);
}
