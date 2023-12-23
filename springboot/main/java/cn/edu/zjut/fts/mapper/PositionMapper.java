package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.Position;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface PositionMapper extends BaseMapper<Position> {

    @Select("select * from positiontable")
    public List<Position> selectall();

    @Insert("insert into positiontable(id, username, bs, pos, C_Pro, R_Pro, F_Price, N_Price, N_Time, Delivery) " +
            "values(#{id}, #{username}, #{bs}, #{pos}, #{C_Pro}, #{R_Pro}, #{F_Price}, #{N_Price}, #{N_Time}, #{Delivery})")
    int insertPosition(Position position);



}
