package cn.edu.zjut.fts.controller;

import cn.edu.zjut.fts.entity.*;
import cn.edu.zjut.fts.mapper.*;
import io.swagger.annotations.Api;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Api(tags = "更新数据模块")
@RestController
public class getFuturesDataController {

    @Autowired
    private a_futuresMapper a_futuresMapper;
    @Autowired
    private ag_futuresMapper ag_futuresMapper;
    @Autowired
    private al_futuresMapper al_futuresMapper;
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private DelegateMapper delegateMapper;
    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private aMapper aMapper;
    @Autowired
    private alMapper alMapper;
    @Autowired
    private agMapper agMapper;

    @PostMapping("/getFuturesData")
    public Future getFuturesData(@RequestBody Position request){
        //0.获取前端信息
        String id =request.getId();
        String username = request.getUsername();


        //1.返回当前时间期货数据
        int Q_id =0;
        Future future=new Future();
        if(id.equals("a")){
            a_Future a_future=new a_Future();
            Q_id = aMapper.geta_Last_Read_PositionId();
            aMapper.updateALastReadPositionId();
            a_future =a_futuresMapper.selecta_AllAttributesByLastReadPositionId(Q_id);
            future.setDate(a_future.getDate());
            future.setTime(a_future.getTime());
            future.setPrice(a_future.getPrice());
            future.setVolume(a_future.getVolume());
        }
        if(id.equals("ag")){
            ag_Future ag_future=new ag_Future();
            Q_id = agMapper.getag_Last_Read_PositionId();
            agMapper.updateAgLastReadPositionId();
            ag_future =ag_futuresMapper.selectag_AllAttributesByLastReadPositionId(Q_id);
            future.setDate(ag_future.getDate());
            future.setTime(ag_future.getTime());
            future.setPrice(ag_future.getPrice());
            future.setVolume(ag_future.getVolume());
        }
        if(id.equals("al")){
            al_Future al_future=new al_Future();
            Q_id = alMapper.getal_Last_Read_PositionId();
            alMapper.updateALLastReadPositionId();
            al_future =al_futuresMapper.selectal_AllAttributesByLastReadPositionId(Q_id);
            future.setDate(al_future.getDate());
            future.setTime(al_future.getTime());
            future.setPrice(al_future.getPrice());
            future.setVolume(al_future.getVolume());
        }
        System.out.println("Q_id:"+Q_id);
        System.out.println("future:"+future);
        // 2.更新委托表
        //2.0获取成交当前价格，当前时间
        Long q_date = future.getDate();
        Time q_time = future.getTime();
        int q_price = future.getPrice();
        //2.1.获取username的id种类的已委记录
        List<Delegate> delegatesToUpdate = delegateMapper.selectDelegatesToUpdate(id,username);
        int f_update=0;//2.2记录是否要更新表单
        for (Delegate delegate : delegatesToUpdate) {
            //2.3更新委托表
             if(delegate.getAtt().equals("买")){
                 if(delegate.getD_Price()>=q_price){
                     delegateMapper.updateDelegateStatusToDone(delegate);
                     f_update=1;
                 }
             }
            if(delegate.getAtt().equals("卖")){
                if(delegate.getD_Price()<=q_price){
                    delegateMapper.updateDelegateStatusToDone(delegate);
                    f_update=1;
                }
            }
            System.out.println(delegate);
            if(f_update==1){
                //3.增加成交表
                Transaction transaction =new Transaction();
                transaction.setId(id);
                transaction.setUsername(username);
                transaction.setBs(delegate.getAtt()+"开");
                transaction.setNum(delegate.getNum());
                transaction.setPrice(delegate.getD_Price());
                transaction.setPremium(25);
                transaction.setPro(delegate.getD_Price()-25);
                // 将long类型的日期转换为java.sql.Date
                Date switce_date = new Date(q_date);
                java.sql.Date sqlDate = new java.sql.Date(switce_date.getTime());
                // 将Time类型的时间转换为java.sql.Time
                java.sql.Time sqlTime = new java.sql.Time(q_time.getTime());
                // 使用java.sql.Timestamp将日期和时间组合在一起
                Timestamp combinedDateTime = new Timestamp(sqlDate.getTime() + sqlTime.getTime());
                transaction.setTime(combinedDateTime);
                System.out.println(transaction);
                transactionMapper.insertTransaction(transaction);

                //4.增加持仓表
                Position position =new Position();
                position.setId(id);
                position.setUsername(username);
                position.setBs(delegate.getAtt());
                position.setPos(delegate.getNum());
                position.setC_Pro(0);
                position.setR_Pro(0.00);
                position.setF_Price(delegate.getD_Price());
                position.setN_Price(q_price);
                position.setN_Time(combinedDateTime);
                // 处理 Delivery 的逻辑
                LocalDateTime nTime = position.getN_Time().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                int dayOfMonth = nTime.getDayOfMonth();

                if (dayOfMonth < 20) {
                    // 如果 N_Time 的日期小于 20，设置 Delivery 为同月的第 20 天
                    LocalDate deliveryDate = nTime.withDayOfMonth(20).toLocalDate();
                    position.setDelivery(Date.from(deliveryDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                } else {
                    // 如果 N_Time 的日期大于等于 20，设置 Delivery 为下一个月的第 20 天
                    LocalDate deliveryDate = nTime.plusMonths(1).withDayOfMonth(20).toLocalDate();
                    position.setDelivery(Date.from(deliveryDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                }
                positionMapper.insertPosition(position);
                System.out.println(position);
            }
        }
        //4.更新持仓表
        List <Position> positionList =positionMapper.selectall();
        for(Position position:positionList){
            int C_pro = (q_price -position.getF_Price())*position.getPos();
            double R_pro = ((q_price - position.getF_Price()) / position.getF_Price()) * 100.0;
            int N_price = q_price;
            System.out.println(R_pro);
            positionMapper.updatePosition(C_pro,R_pro,N_price);
        }
        return future;
    }

}
