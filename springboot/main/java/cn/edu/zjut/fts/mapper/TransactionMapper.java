package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.Transaction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransactionMapper extends BaseMapper<Transaction>
{

    @Insert("INSERT INTO Transaction(delegateid, serviceFee, transactionTime) " + "VALUES(#{delegateid}, #{serviceFee}, #{transactionTime})")
    int insertTransaction(Transaction transaction);
}
