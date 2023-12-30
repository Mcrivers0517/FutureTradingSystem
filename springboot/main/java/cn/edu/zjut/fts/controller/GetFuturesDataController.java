package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.*;
import cn.edu.zjut.fts.mapper.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Api(tags = "更新数据模块")
@CrossOrigin
@RestController
public class GetFuturesDataController
{

    @Autowired
    private AuFuturesMapper auFuturesMapper;
    @Autowired
    private AgFuturesMapper agFuturesMapper;
    @Autowired
    private AlFuturesMapper alFuturesMapper;
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private DelegateMapper delegateMapper;
    @Autowired
    private TransactionMapper transactionMapper;

    private static int currentRowAu = 1;
    private static int currentRowAg = 1;
    private static int currentRowAl = 1;

    @PostMapping("/getFuturesData")
    public Future getFuturesData(@RequestBody Request request)
    {
        //0.获取前端信息
        Date date = request.getDate();
        Time time = request.getTime();
        String futureName = request.getFutureName();
        String username = request.getUsername();


        //1.返回当前时间期货数据
        Future future = new Future();
        if (futureName.equals("au"))
        {
            AuFuture auFuture = auFuturesMapper.selectAuAllAttributesById(currentRowAu); //从数据库中读取期货数据
            currentRowAu += 1;
            //通过赋值转换数据类型为future
            future.setPrice(auFuture.getPrice());
            future.setVolume(auFuture.getVolume());
        }
        else if (futureName.equals("ag"))
        {
            AgFuture agFuture = agFuturesMapper.selectAgAllAttributesById(currentRowAg); //从数据库中读取期货数据
            currentRowAg += 1;
            future.setPrice(agFuture.getPrice());
            future.setVolume(agFuture.getVolume());
        }
        else
        {
            AlFuture alFuture = alFuturesMapper.selectAlAllAttributesById(currentRowAl); //从数据库中读取期货数据
            currentRowAl += 1;
            future.setPrice(alFuture.getPrice());
            future.setVolume(alFuture.getVolume());
        }

        // 2.更新委托表
        //2.0获取成交当前价格，当前时间
        int currentPrice = future.getPrice();
        //2.1.获取username的futureName种类的已委记录
        List<Delegate> delegatesToUpdate = delegateMapper.selectDelegatesToUpdate(futureName, username);
        Boolean whetherToUpdate = false;//2.2记录是否要更新表单
        for (Delegate delegate : delegatesToUpdate)
        {
            //2.3更新委托表
            if (delegate.getAtt().equals("buy"))
            {
                if (delegate.getDelegatePrice() >= currentPrice)
                {
                    delegateMapper.updateDelegateStatusToDone(delegate);
                    whetherToUpdate = true;
                }
            }
            else if (delegate.getAtt().equals("sell"))
            {
                if (delegate.getDelegatePrice() <= currentPrice)
                {
                    delegateMapper.updateDelegateStatusToDone(delegate);
                    whetherToUpdate = true;
                }
            }
            System.out.println(delegate);

            // 3.如果交易达成，那么更新成交表transactionTable和持仓表positionTable中的记录
            if (whetherToUpdate)
            {
                // 增加成交表
                Transaction transaction = new Transaction();
                transaction.setFutureName(futureName);
                transaction.setUsername(username);
                transaction.setBs(delegate.getAtt() + "2out");
                transaction.setNum(delegate.getNum());
                transaction.setPrice(delegate.getDelegatePrice());
                transaction.setPremium(25);     //为简化程序，手续费固定为25
                transaction.setProfit(transaction.getPrice() - 25); //利润=成交价-手续费

                // 将long类型的日期转换为java.sql.Date
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                // 将Time类型的时间转换为java.sql.Time
                java.sql.Time sqlTime = new java.sql.Time(time.getTime());
                // 使用java.sql.Timestamp将日期和时间组合在一起
                Timestamp combinedDateTime = new Timestamp(sqlDate.getTime() + sqlTime.getTime());
                transaction.setTime(combinedDateTime);
                System.out.println(transaction);
                transactionMapper.insertTransaction(transaction);

                //4.更新持仓表
//                Position position = positionMapper.selectByFutureName(futureName);
                Position position = new Position();
                position.setFutureName(delegate.getFutureName());
                position.setUsername(delegate.getUsername());
                position.setBs(delegate.getAtt());
                position.setAmount(delegate.getNum());
                position.setFloatingProfitAndLoss(0);
                position.setProfitLossRatio(0.00);
                position.setOpenPrice(delegate.getDelegatePrice());
                position.setCurrentPrice(currentPrice);
                position.setCurrentTime(combinedDateTime);

                // 处理 deliveryDate 的逻辑
                LocalDateTime nTime = position.getCurrentTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                int dayOfMonth = nTime.getDayOfMonth();

                if (dayOfMonth < 20)
                {
                    // 如果 currentTime 的日期小于 20，设置 deliveryDate 为同月的第 20 天
                    LocalDate deliveryDateDate = nTime.withDayOfMonth(20).toLocalDate();
                    position.setDeliveryDate(Date.from(deliveryDateDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                }
                else
                {
                    // 如果 currentTime 的日期大于等于 20，设置 deliveryDate 为下一个月的第 20 天
                    LocalDate deliveryDateDate = nTime.plusMonths(1).withDayOfMonth(20).toLocalDate();
                    position.setDeliveryDate(Date.from(deliveryDateDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                }
                positionMapper.insertPosition(position);
                System.out.println(position);
            }
        }
        //4.更新持仓表
        List<Position> positionList = positionMapper.selectall();
        DecimalFormat df = new DecimalFormat("#.##");
        for (Position position : positionList)
        {
            int floatingProfitAndLoss = (currentPrice - position.getOpenPrice()) * position.getAmount();
            double profitLossRatio = ((currentPrice - position.getOpenPrice()) / position.getOpenPrice());
            profitLossRatio = Double.parseDouble(df.format(profitLossRatio)); // 格式化为两位小数
            System.out.println(profitLossRatio);

            positionMapper.updatePosition(floatingProfitAndLoss, profitLossRatio, currentPrice);
        }
        return future;
    }


}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Request
{
    Date date;
    Time time;
    String futureName;
    String username;
}