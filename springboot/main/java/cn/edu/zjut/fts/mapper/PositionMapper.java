package cn.edu.zjut.fts.mapper;

import cn.edu.zjut.fts.entity.Position;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface PositionMapper extends BaseMapper<Position> {

    @Select("select * from positiontable")
    public List<Position> selectall();

    @Insert("insert into positiontable(id, username, bs, pos, C_Pro, R_Pro, F_Price, N_Price, N_Time, Delivery) " +
            "values(#{id}, #{username}, #{bs}, #{pos}, #{C_Pro}, #{R_Pro}, #{F_Price}, #{N_Price}, #{N_Time}, #{Delivery})")
    int insertPosition(Position position);

    @Update("update positiontable set C_Pro = #{C_Pro}, R_Pro = #{R_Pro}, N_Price = #{N_Price}")
    int updatePosition(@Param("C_Pro") int C_Pro, @Param("R_Pro") double R_Pro, @Param("N_Price") int N_Price);




}
