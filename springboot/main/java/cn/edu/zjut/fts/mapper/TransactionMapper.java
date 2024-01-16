package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.TransactionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransactionMapper extends BaseMapper<TransactionEntity>
{

    @Insert("INSERT INTO Transaction(delegateid, serviceFee, transactionTime) " + "VALUES(#{delegateid}, #{serviceFee}, #{transactionTime})")
    int insertTransaction(TransactionEntity transactionEntity);
}
