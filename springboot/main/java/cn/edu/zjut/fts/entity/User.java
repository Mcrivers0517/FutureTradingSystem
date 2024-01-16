package cn.edu.zjut.fts.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User
{

    private int userId;
    private String username;
    private String password;
    private String avatarUrl;
}
