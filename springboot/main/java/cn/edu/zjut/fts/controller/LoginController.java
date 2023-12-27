package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.User;
import cn.edu.zjut.fts.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public Result login(@RequestBody User request) {
        Result result = new Result();
        Boolean success = userMapper.selectByUsername(request);
        result.setSuccess(success);
        return result;
    }

    // 定义一个用于包装返回结果的类
    private static class Result {
        private Boolean success;

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }
    }
}
