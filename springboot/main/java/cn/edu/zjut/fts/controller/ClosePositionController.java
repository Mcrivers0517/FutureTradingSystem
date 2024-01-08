package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.ClosePositionRequest;
import cn.edu.zjut.fts.entity.Delegate;
import cn.edu.zjut.fts.entity.Position;
import cn.edu.zjut.fts.entity.Transaction;
import cn.edu.zjut.fts.mapper.DelegateMapper;
import cn.edu.zjut.fts.mapper.PositionMapper;
import cn.edu.zjut.fts.mapper.TransactionMapper;
import cn.edu.zjut.fts.mapper.UserMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Api(tags = "平仓模块")
@CrossOrigin
@RestController
public class ClosePositionController
{

    @Autowired
    private DelegateMapper delegateMapper;

    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/ClosePosition")
    public void closePosition(@RequestBody ClosePositionRequest request)
            throws ParseException
    {
        System.out.println(request);
        //获取持仓表id和平仓数量sellAmount
        int positionId = request.getPositionId();
        int sellAmount = request.getSellAmount();
        String dateTimeString = request.getDelegateTime();

        //时间的格式处理
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

        //获取持仓表中对应id的数据
        Position position = positionMapper.getPositionById(positionId);
        System.out.println(position);

        //根据positionId找到对应的持仓记录对Amount进行减去sellAmount操作
        positionMapper.updateAmountPositionById(positionId, sellAmount);

        //添加平仓的委托记录
        Delegate delegate = new Delegate();
        System.out.println("buy2open".equals(position.getAttribute()));

        //设置attribute
        if ("buy2open".equals(position.getAttribute()))
        {
            delegate.setAttribute("buy2close");
        }
        else
        {
            delegate.setAttribute("sell2close");
        }
        //设置status,amount,delegatePrice,delegateTime,FutureId,UserId
        delegate.setStatus("已成");
        delegate.setAmount(sellAmount);
        delegate.setDelegatePrice(position.getCurrentPrice());
        delegate.setDelegateTime(formattedDateTime);
        delegate.setFutureId(position.getFutureId());
        delegate.setUserId(position.getUserId());

        //设置交割日期
        int dayOfMonth = date.getDayOfMonth();

        if (dayOfMonth < 20)
        {
            // 如果 currentTime 的日期小于 20，设置 deliveryDate 为同月的第 20 天
            LocalDate deliveryDate = date.withDayOfMonth(20);
            String formattedDeliveryDate = deliveryDate.format(dataFormatter); // 格式化日期部分
            delegate.setDeliveryDate(formattedDeliveryDate);
        }
        else
        {
            // 如果 currentTime 的日期大于等于 20，设置 deliveryDate 为下一个月的第 20 天
            LocalDate deliveryDate = date.plusMonths(1).withDayOfMonth(20);
            String formattedDeliveryDate = deliveryDate.format(dataFormatter);
            delegate.setDeliveryDate(formattedDeliveryDate);
        }

        //插入委托记录
        System.out.println(delegate);
        delegateMapper.insertDelegate(delegate);

        //更新成交记录
        Transaction transaction = new Transaction();
        transaction.setServiceFee(25);
        transaction.setTransactionTime(formattedDateTime);
        transaction.setDelegateid(delegateMapper.selectDelegateIdByTime(delegate.getDelegateTime()));
        System.out.println(transaction);
        transactionMapper.insertTransaction(transaction);
    }
}
