package cn.edu.zjut.fts.service;

import cn.edu.zjut.fts.entity.PositionEntity;

import java.util.List;

public interface PositionService
{

    int insertPosition(PositionEntity positionEntity);

    int updatePosition(PositionEntity positionEntity);

    PositionEntity getByFutureIdAndAttribute(int currentIndex, int userId, String attribute);

    PositionEntity getPositionById(int positionId);

    boolean updateAmountPositionById(int positionId, int sellAmount);

    List<PositionEntity> getPositionByUserId(int userId);

    List<PositionEntity> getPositionsByFutureId(int futureId);
}
