package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.CreateDelegateControllerResponse;
import cn.edu.zjut.fts.entity.Delegate;
import cn.edu.zjut.fts.mapper.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.edu.zjut.fts.entity.DelegateRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Api(tags = "买卖模块")
@CrossOrigin
@RestController
public class CreateDelegateController
{

    @Autowired
    private DelegateMapper delegateMapper;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/createDelegate")
    public CreateDelegateControllerResponse buyOrSell(@RequestBody DelegateRequest request)
            throws ParseException
    {
        System.out.println(request);
        int futureid = request.getFutureId();
        int userid = request.getUserId();
        String attribute = request.getAttribute();
        int amount = request.getAmount();
        double delegatePrice = request.getDelegatePrice();
        String dateTimeString = request.getDateTimeString();

        CreateDelegateControllerResponse createDelegateResult = new CreateDelegateControllerResponse(true);
        double Deposit = userMapper.getDeposit(userid);
        if (Deposit < amount * delegatePrice * 0.1)
        {
            createDelegateResult.setResult(false);
            return createDelegateResult;
        }
        ;
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        Date completeDateTime = dateTimeFormat.parse(dateTimeString);

        // 使用 LocalDateTime 直接获取日期和时间部分
        LocalDateTime localDateTime = completeDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(); // 转换为 LocalDateTime

        LocalDate date = localDateTime.toLocalDate(); // 获取日期部分（年月日）
        LocalTime time = localDateTime.toLocalTime(); // 获取时间部分（时分秒）

        DateTimeFormatter dataTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // 将 LocalDate、LocalTime 和 LocalDateTime 格式化为字符串
        String formattedDate = date.format(dataFormatter); // 格式化日期部分
        String formattedTime = time.format(timeFormatter); // 格式化时间部分
        String formattedDateTime = localDateTime.format(dataTimeFormatter); // 格式化日期时间部分


        // 1. 插入委托数据
        Delegate delegate = new Delegate();
        delegate.setFutureId(futureid);
        delegate.setUserId(userid);
        delegate.setAttribute(attribute + "2open");
        delegate.setStatus("已委");
        delegate.setAmount(amount);
        delegate.setDelegatePrice(delegatePrice);
        delegate.setDelegateTime(formattedDateTime);
        delegateMapper.insertDelegate(delegate);

        //修改持仓资产
        double productvalue = amount * delegatePrice;
        userMapper.updateInitialCapitalByUserID(userid, productvalue);
        userMapper.updateDepositByUserID(userid, -25);
        return createDelegateResult;
    }
}


