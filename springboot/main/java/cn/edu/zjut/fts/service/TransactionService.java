package cn.edu.zjut.fts.service;

import cn.edu.zjut.fts.entity.Transaction;
import cn.edu.zjut.fts.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService
{

    private final TransactionMapper transactionMapper;

    @Autowired
    public TransactionService(TransactionMapper transactionMapper)
    {
        this.transactionMapper = transactionMapper;
    }

    public int addTransaction(Transaction transaction)
    {
        // 调用TransactionMapper的insertTransaction方法插入交易记录
        return transactionMapper.insertTransaction(transaction);
    }
}
