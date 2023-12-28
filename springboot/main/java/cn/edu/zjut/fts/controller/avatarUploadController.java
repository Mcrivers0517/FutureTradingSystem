package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.User;
import cn.edu.zjut.fts.mapper.UserMapper;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin
@RestController
public class avatarUploadController
{
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/uploadAvatar")
    @ApiOperation(value = "上传文件并保存", notes = "测试上传功能")
    public void uploadImg(@RequestParam MultipartFile file, @RequestParam String username)
    {
        System.out.println(username);
        try
        {
            // 要保存到的目标路径
            String targetDirectory = "D:/ProgramFiles/nodejs/vue_projects/future-trading-system/vue/src/assets/avatar/";

            // 如果目录不存在，就创建目录
            Path targetDirPath = Paths.get(targetDirectory);
            if (!Files.exists(targetDirPath))
            {
                Files.createDirectories(targetDirPath);
            }

            // 构建文件路径
            String targetFilePath = targetDirectory + file.getOriginalFilename();

            // 保存文件到指定路径
            byte[] fileBytes = file.getBytes();
            Path targetFilePathObj = Paths.get(targetFilePath);
            Files.write(targetFilePathObj, fileBytes);

            System.out.println(targetFilePathObj);

            String avatarUrl = targetFilePathObj.toString();

            System.out.println(avatarUrl);

            int rowsAffected = userMapper.updateAvatarUrlByUsername(username, avatarUrl);
            if (rowsAffected > 0)
            {
                System.out.println("success");
            }
            else
            {
                System.out.println("fail");
            }
        }
        catch (Exception e)
        {
            System.out.println("exception");
        }
    }

    @PostMapping("/getUserAvatar")
    @ApiOperation(value = "获取用户头像", notes = "根据用户名获取用户头像")
    public ResponseEntity<byte[]> getUserAvatar(@RequestBody User request)
    {
        try
        {
            String username = request.getUsername();
            String avatarUrl = userMapper.getAvatarUrlByUsername(username);

            if (avatarUrl != null)
            {
                // 读取图像文件内容
                File imageFile = new File(avatarUrl);
                byte[] imageBytes = Files.readAllBytes(imageFile.toPath());

                // 设置响应头
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // 设置 MIME 类型，可以根据实际情况修改

                // 返回图像文件的内容和响应头
                return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
            }
            else
            {
                // 用户没有头像，返回默认图像或者其他处理
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e)
        {
            // 发生异常时的处理
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class AvatarResponse
    {
        private String avatarUrl;
    }

}
