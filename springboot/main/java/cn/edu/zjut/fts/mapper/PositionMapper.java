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

    @Insert("insert into positiontable(id, username, BS, pos, c_Pro, r_Pro, f_Price, n_Price, n_Time,Delivery) " +
            "values(#{id}, #{username}, #{BS}, #{pos}, #{c_Pro}, #{r_Pro}, #{f_Price}, #{n_Price}, #{n_Time},#{Delivery)")
    int insertPosition(Position position);


}
