package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.Position;
import cn.edu.zjut.fts.mapper.PositionMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Api(tags = "持仓模块")
@RestController
public class PositionController {

    @Autowired
    private PositionMapper positionMapper;

    @GetMapping("/getpositions")
    public List selectAll() {
        List<Position> list = positionMapper.selectall();
        //System.out.println(list);
        return list;
    }

    @PostMapping("/insertPosition")
    public int insertPosition(@RequestBody Position position) {
        // 设置默认值
        position.setC_Pro(0);
        position.setR_Pro(0);
        position.setN_Price(position.getF_Price());

        // 设置 Delivery 默认值为 20200720
        Date defaultDelivery = new Date(120, 6, 20); // 注意月份是从0开始计算的，所以6表示7月
        position.setDelivery(defaultDelivery);

        // 如果 n_Time 大于 20200720，将 Delivery 设置为 20200820
        if (position.getN_Time().after(defaultDelivery)) {
            Date updatedDelivery = new Date(120, 7, 20); // 8月
            position.setDelivery(updatedDelivery);
        }

        return positionMapper.insertPosition(position);
    }

}
