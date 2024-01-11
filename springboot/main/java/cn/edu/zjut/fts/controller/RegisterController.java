package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.User;
import cn.edu.zjut.fts.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {
    @Autowired
    private UserMapper userMapper;

//查询所有用户
//    @GetMapping("/user")
//    public  List query(){
//
//        List<User> list = userMapper.selectall();
//        System.out.println(list);
//        return list;
//    }

@PostMapping("/register")
public String insert(@RequestParam("username") String username,
                     @RequestParam("password") String password) {
    try {
        // 检查两次输入的密码是否相同

        // 创建 User 对象并设置属性
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        // 直接保存用户信息到数据库
        int result = userMapper.insert(user);
        if (result > 0) {
            return "注册成功";
        } else {
            return "注册失败";
        }
    } catch (Exception e) {
        // 适当处理异常（记录或返回有意义的错误消息）
        return "注册过程中发生错误";
    }
}


}

