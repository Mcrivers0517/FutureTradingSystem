package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.Transaction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TransactionMapper extends BaseMapper<Transaction>
{

    @Select("SELECT * FROM TransactionTable")
    List<Transaction> selectAll();

    @Insert("INSERT INTO TransactionTable(delegateid, serviceFee, transactionTime) " + "VALUES(#{delegateid}, #{serviceFee}, #{transactionTime})")
    int insertTransaction(Transaction transaction);


}
