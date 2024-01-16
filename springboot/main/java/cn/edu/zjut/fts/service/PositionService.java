package cn.edu.zjut.fts.service;

import cn.edu.zjut.fts.entity.Position;
import cn.edu.zjut.fts.mapper.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService
{

    private final PositionMapper positionMapper;

    @Autowired
    public PositionService(PositionMapper positionMapper)
    {
        this.positionMapper = positionMapper;
    }

    public int insertPosition(Position position)
    {
        // 调用PositionMapper的insertPosition方法插入持仓信息
        return positionMapper.insertPosition(position);
    }

    public int updatePosition(Position position)
    {
        // 调用PositionMapper的updatePosition方法更新持仓信息
        return positionMapper.updatePosition(position);
    }

    public Position getByFutureIdAndAttribute(int currentIndex, int userId, String attribute)
    {
        // 调用PositionMapper的selectByFutureIdAndAttribute方法查询持仓信息
        return positionMapper.selectByFutureIdAndAttribute(currentIndex, userId, attribute);
    }

    public Position getPositionById(int positionId)
    {
        // 调用PositionMapper的getPositionById方法查询持仓信息
        return positionMapper.getPositionById(positionId);
    }

    public boolean updateAmountPositionById(int positionId, int sellAmount)
    {
        // 调用PositionMapper的updateAmountPositionById方法更新持仓数量
        return positionMapper.updateAmountPositionById(positionId, sellAmount);
    }

    public List<Position> getPositionByUserId(int userId)
    {
        // 调用PositionMapper的getPositionByUserId方法查询用户的所有持仓信息
        return positionMapper.getPositionByUserId(userId);
    }

    public List<Position> getPositionsByFutureId(int futureId)
    {
        // 调用PositionMapper的selectAPositionsByFutureId方法查询特定期货的所有持仓信息
        return positionMapper.selectAPositionsByFutureId(futureId);
    }
}
