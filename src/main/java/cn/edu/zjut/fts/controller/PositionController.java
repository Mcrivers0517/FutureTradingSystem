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
        return positionMapper.insertPosition(position);
    }
}
