package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.Position;
import cn.edu.zjut.fts.mapper.PositionMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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
        position.setR_Pro(0.00);
        position.setN_Price(position.getF_Price());

        // 处理 Delivery 的逻辑
        LocalDateTime nTime = position.getN_Time().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        int dayOfMonth = nTime.getDayOfMonth();

        if (dayOfMonth < 20) {
            // 如果 N_Time 的日期小于 20，设置 Delivery 为同月的第 20 天
            LocalDate deliveryDate = nTime.withDayOfMonth(20).toLocalDate();
            position.setDelivery(Date.from(deliveryDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        } else {
            // 如果 N_Time 的日期大于等于 20，设置 Delivery 为下一个月的第 20 天
            LocalDate deliveryDate = nTime.plusMonths(1).withDayOfMonth(20).toLocalDate();
            position.setDelivery(Date.from(deliveryDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        System.out.println(position);
        return positionMapper.insertPosition(position);
    }

}
