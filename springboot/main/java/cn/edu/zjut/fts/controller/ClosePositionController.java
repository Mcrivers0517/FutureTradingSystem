package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.request.ClosePositionRequest;
import cn.edu.zjut.fts.entity.DelegateEntity;
import cn.edu.zjut.fts.entity.PositionEntity;
import cn.edu.zjut.fts.entity.TransactionEntity;
import cn.edu.zjut.fts.response.ClosePositionResponse;
import cn.edu.zjut.fts.service.DelegateService;
import cn.edu.zjut.fts.service.PositionServiceImpl;
import cn.edu.zjut.fts.service.TransactionServiceImpl;
import cn.edu.zjut.fts.service.UserServiceImpl;
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
    private DelegateService delegateService;

    @Autowired
    private PositionServiceImpl positionServiceImpl;

    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/closePosition")
    public ClosePositionResponse closePosition(@RequestBody ClosePositionRequest request)
            throws ParseException
    {
        try
        {
            System.out.println(request);
            int positionId = request.getPositionId();
            int closeAmount = request.getCloseAmount();
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
            PositionEntity positionEntity = positionServiceImpl.getPositionById(positionId);
//        System.out.println(position);

            //根据positionId找到对应的持仓记录对Amount进行减去closeAmount操作
            positionServiceImpl.updateAmountPositionById(positionId, closeAmount);

            //添加平仓的委托记录
            DelegateEntity delegateEntity = new DelegateEntity();
            System.out.println("buy2open".equals(positionEntity.getAttribute()));

            //设置attribute
            if ("buy2open".equals(positionEntity.getAttribute()))
            {
                delegateEntity.setAttribute("buy2close");
            }
            else
            {
                delegateEntity.setAttribute("sell2close");
            }
            //设置status,amount,delegatePrice,delegateTime,FutureId,UserId
            delegateEntity.setStatus("已成");
            delegateEntity.setAmount(closeAmount);
            delegateEntity.setDelegatePrice(positionEntity.getCurrentPrice());
            delegateEntity.setDelegateTime(formattedDateTime);
            delegateEntity.setFutureId(positionEntity.getFutureId());
            delegateEntity.setUserId(positionEntity.getUserId());

            //设置交割日期
            int dayOfMonth = date.getDayOfMonth();

            if (dayOfMonth < 20)
            {
                // 如果 currentTime 的日期小于 20，设置 deliveryDate 为同月的第 20 天
                LocalDate deliveryDate = date.withDayOfMonth(20);
                String formattedDeliveryDate = deliveryDate.format(dataFormatter); // 格式化日期部分
                delegateEntity.setDeliveryDate(formattedDeliveryDate);
            }
            else
            {
                // 如果 currentTime 的日期大于等于 20，设置 deliveryDate 为下一个月的第 20 天
                LocalDate deliveryDate = date.plusMonths(1).withDayOfMonth(20);
                String formattedDeliveryDate = deliveryDate.format(dataFormatter);
                delegateEntity.setDeliveryDate(formattedDeliveryDate);
            }

            //插入委托记录
            System.out.println(delegateEntity);
            delegateService.addDelegate(delegateEntity);

            //更新成交记录
            TransactionEntity transactionEntity = new TransactionEntity();
            transactionEntity.setServiceFee(25);
            transactionEntity.setTransactionTime(formattedDateTime);
            transactionEntity.setDelegateid(delegateService.getDelegateIdByTime(delegateEntity.getDelegateTime()));
            System.out.println(transactionEntity);
            transactionServiceImpl.addTransaction(transactionEntity);

            //更新用户钱包
            double userProfit = closeAmount * positionEntity.getProfitLoss() - 25;
            double productProfit = closeAmount * positionEntity.getEntryPrice() * (-1);
            System.out.println(userProfit + "   " + productProfit);
            userServiceImpl.updateDepositByUserID(positionEntity.getUserId(), userProfit);
            userServiceImpl.updateInitialCapitalByUserID(positionEntity.getUserId(), productProfit);

            return new ClosePositionResponse(true);
        }
        catch (Exception e)

        {
            return new ClosePositionResponse(false);
        }
    }
}
