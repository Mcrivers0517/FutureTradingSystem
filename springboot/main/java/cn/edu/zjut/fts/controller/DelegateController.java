package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.Delegate;
import cn.edu.zjut.fts.entity.Position;
import cn.edu.zjut.fts.mapper.DelegateMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Api(tags = "委托模块")
@RestController
public class DelegateController {

    @Autowired
    private DelegateMapper DelegateMapper;

    @GetMapping("/getdelegates")
    public List selectAll() {
        List<Delegate> list = DelegateMapper.selectall();
        //System.out.println(list);
        return list;
    }

}
