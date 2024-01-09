package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.*;
import cn.edu.zjut.fts.mapper.*;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(tags = "更新数据模块")
@CrossOrigin
@RestController
public class GetFuturesDataController
{
    @Autowired
    private FuturesMapper futuresMapper;
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private DelegateMapper delegateMapper;
    @Autowired
    private TransactionMapper transactionMapper;

    static List<Integer> currentRowList = new ArrayList<>();

    @PostMapping("/getFuturesData")
    public List<Futures> getFuturesData(@RequestBody GetFutureDataRequest request)
            throws ParseException
    {
        //0.获取前端信息
        List<String> tableNames = futuresMapper.selectAllTableName();

        String dateTimeString = request.getDateTimeString();

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

        System.out.println("日期部分：" + formattedDate);
        System.out.println("时间部分：" + formattedTime);
        System.out.println("日期时间部分：" + formattedDateTime);

        //1.返回当前时间期货数据

        List<Futures> futuresList = new ArrayList<>();

        int currentIndex = 0;
        for (String tableName : tableNames)
        {
            if (currentRowList.size() < currentIndex + 1)   // 如果这个期货的指针还没有被建立
            {
                currentRowList.add(1);
            }

//            System.out.println(tableName);
            Futures futures = futuresMapper.selectAllByFutureAndId(tableName, currentRowList.get(currentIndex));

//            System.out.println(currentRowList.get(currentIndex));

            // 读第一条数据时，初始化开盘价，以及当日最高价和当日最低价
            if (currentRowList.get(currentIndex) == 1)
            {
                futures.setDailyOpenPrice(futures.getPrice());
                futures.setDailyHighestPrice(futures.getPrice());
                futures.setDailyLowestPrice(futures.getPrice());
                futuresMapper.updateDailyOpenPrice(futures.getPrice(), currentIndex + 1);
                futuresMapper.updateDailyPrice(futures.getPrice(), futures.getPrice(), currentIndex + 1);
            }
            // 时间变为0点时，重新设置开盘价，并重新初始化当日最高价和当日最低价
            else if(formattedTime.equals("00:00:00"))
            {
                futures.setDailyOpenPrice(futures.getPrice());
                futures.setDailyHighestPrice(futures.getPrice());
                futures.setDailyLowestPrice(futures.getPrice());
                futuresMapper.updateDailyOpenPrice(futures.getPrice(), currentIndex + 1);
                futuresMapper.updateDailyPrice(futures.getPrice(), futures.getPrice(), currentIndex + 1);
            }


            int currentValue = currentRowList.get(currentIndex); // 获取当前索引的值
            currentValue += 1; // 增加值
            currentRowList.set(currentIndex, currentValue); // 将增加后的值放回列表中

            // 从数据库中获得该期货的开盘价、最高价、最低价
            futures.setDailyOpenPrice(futuresMapper.selectDailyOpenPriceByFutureId(currentIndex + 1));
            futures.setDailyHighestPrice(futuresMapper.selectDailyHighestPriceByFutureId(currentIndex + 1));
            futures.setDailyLowestPrice(futuresMapper.selectDailyLowestPriceByFutureId(currentIndex + 1));

            // 如果期货的当前价格超过了当日最高价格，则更新最高价格
            System.out.println(futures.getPrice());
            if (futures.getPrice() > futuresMapper.selectDailyHighestPriceByFutureId(currentIndex + 1))
            {
                futures.setDailyHighestPrice(futures.getPrice());
            }
            // 如果期货的当前价格跌破了当日最低价格，则更新最低价格
            if (futures.getPrice() < futuresMapper.selectDailyLowestPriceByFutureId(currentIndex + 1))
            {
                futures.setDailyLowestPrice(futures.getPrice());
            }
            // 更新数据库中的每日最高价和每日最低价
            futuresMapper.updateDailyPrice(futures.getDailyHighestPrice(), futures.getDailyLowestPrice(), currentIndex + 1);

//            futures.setPrice(futures.getPrice());
//            futures.setVolume(futures.getVolume());



            // 2.更新委托表
            //2.0获取成交当前价格，当前时间
            double currentPrice = futures.getPrice();
            //2.1.获取userid的futureid种类的已委记录
            List<Delegate> delegates = delegateMapper.selectDelegatesByFutureAndUser(currentIndex);
            boolean whetherToUpdate = false;    //2.2记录是否要更新表单
            // 遍历委托表，寻找达到成交要求的委托订单记录
            for (Delegate delegate : delegates)
            {
                //2.3更新委托表
                if (delegate.getAttribute().equals("buy2open"))
                {
                    if (delegate.getDelegatePrice() >= currentPrice)
                    {
                        delegateMapper.updateDelegateStatusToDone(delegate.getDelegateId());    // 将符合条件的订单之状态设置为“已成”
                        whetherToUpdate = true;
                    }
                }
                else if (delegate.getAttribute().equals("sell2open"))
                {
                    if (delegate.getDelegatePrice() <= currentPrice)
                    {
                        delegateMapper.updateDelegateStatusToDone(delegate.getDelegateId());    // 将符合条件的订单之状态设置为“已成”
                        whetherToUpdate = true;
                    }
                }
                System.out.println(delegate);

                // 3.如果交易达成，那么更新成交表transactionTable和持仓表positionTable中的记录
                if (whetherToUpdate)
                {

                    // 设置交割日期
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

                    delegateMapper.updateDelegateDeliveryDate(delegate.getDelegateId(), delegate.getDeliveryDate());

                    // 在成交表中增加成交记录
                    Transaction transaction = new Transaction();
                    transaction.setDelegateid(delegate.getDelegateId()); //绑定委托订单
                    transaction.setServiceFee(25.00);   //为简化程序，手续费暂时固定为25
                    transaction.setTransactionTime(formattedDateTime);
                    System.out.println(transaction);
                    transactionMapper.insertTransaction(transaction);

                    //4.更新持仓表
                    Position position = positionMapper.selectByFutureNameAndAttribute(currentIndex, delegate.getUserId(), delegate.getAttribute());
                    if (position != null)
                    {
                        double originalCost = position.getCostPrice();
                        double originalAmount = position.getAmount();
                        position.setAmount(position.getAmount() + delegate.getAmount());    // 最新的持仓量等于原持仓量加委托购买量
                        position.setCurrentPrice(currentPrice);
                        position.setLastUpdate(formattedDateTime);
                        position.setProfitLoss((currentPrice - position.getCostPrice() * position.getAmount())); //浮动盈亏 = (当前价格 - 持仓成本) * 持仓数量
                        position.setProfitLossRatio(position.getProfitLoss() / position.getCostPrice() * 100);  //浮动盈亏率 = 浮动盈亏 / 持仓成本 * 100
                        double totalCost = originalCost * originalAmount + position.getCurrentPrice() * delegate.getAmount() + transaction.getServiceFee(); // 总成本 = 原始成本 × 原始数量 + 新成本 × 新数量 + 手续费
                        position.setCostPrice(totalCost / position.getAmount());    // 新的持仓成本 = 总成本 / 总数量
                        positionMapper.updatePosition(position);
                    }
                    else
                    {
                        position = new Position();
                        position.setUserId(delegate.getUserId());
                        position.setFutureId(delegate.getFutureId());
                        position.setAmount(delegate.getAmount());
                        position.setEntryPrice(currentPrice);
                        position.setCurrentPrice(currentPrice);
                        position.setProfitLoss(0);
                        position.setProfitLossRatio(0);
                        position.setCostPrice(currentPrice);
                        position.setEntryDate(formattedDateTime);
                        position.setLastUpdate(formattedDateTime);
                        position.setAttribute(delegate.getAttribute());
                        positionMapper.insertPosition(position);
                    }
                }
            }
            currentIndex++;

            futuresList.add(futures);
        }
        return futuresList;
    }
}

