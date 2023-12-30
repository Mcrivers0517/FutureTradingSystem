package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.Transaction;
import cn.edu.zjut.fts.mapper.TransactionMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags = "获取历史订单模块")
@CrossOrigin
@RestController
public class GetHistoricalOrdersController
{
    @Autowired
    private TransactionMapper transactionMapper;

    @GetMapping("/getHistoricalOrders")
    public List selectAll() {
        List<Transaction> list = transactionMapper.selectAll();
        //System.out.println(list);
        return list;
    }
}
