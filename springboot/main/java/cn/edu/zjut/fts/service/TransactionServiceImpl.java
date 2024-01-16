package cn.edu.zjut.fts.service;

import cn.edu.zjut.fts.entity.TransactionEntity;
import cn.edu.zjut.fts.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService
{

    private final TransactionMapper transactionMapper;

    @Autowired
    public TransactionServiceImpl(TransactionMapper transactionMapper)
    {
        this.transactionMapper = transactionMapper;
    }

    public int addTransaction(TransactionEntity transactionEntity)
    {
        // 调用TransactionMapper的insertTransaction方法插入交易记录
        return transactionMapper.insertTransaction(transactionEntity);
    }
}
