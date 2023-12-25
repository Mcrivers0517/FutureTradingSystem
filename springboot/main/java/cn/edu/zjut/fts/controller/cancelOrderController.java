package cn.edu.zjut.fts.controller;
import cn.edu.zjut.fts.entity.Delegate;
import cn.edu.zjut.fts.mapper.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "撤销委托订单模块")
@RestController
public class cancelOrderController {

    @Autowired
    private DelegateMapper delegateMapper;

    @PostMapping("/cancelOrder")
    public String cancelOrder(@RequestBody  Delegate delegate) {
        System.out.println(delegate);
        String text="";
        String T_status = delegateMapper.selectstatus(delegate);
        System.out.println(T_status);
        if ("已委".equals(T_status)) {
            delegateMapper.updatestatus(delegate);
            text = "撤销订单成功";
        } else {
            text = "订单已成，不能撤回";
        }
        System.out.println(text);
        return text;
    }
}
