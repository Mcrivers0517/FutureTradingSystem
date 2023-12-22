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
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        try {
            // 根据用户名查询用户
            User user = userMapper.selectByUsername(username);

            // 检查用户是否存在
            if (user == null) {
                return "用户不存在";
            }

            // 检查密码是否正确
            if (!user.getPassword().equals(password)) {
                return "密码错误";
            }

            // 登录成功，可以返回一些用户信息或者token等
            return "登录成功";
        } catch (Exception e) {
            // 适当处理异常（记录或返回有意义的错误消息）
            return "登录过程中发生错误";
        }
    }
}

