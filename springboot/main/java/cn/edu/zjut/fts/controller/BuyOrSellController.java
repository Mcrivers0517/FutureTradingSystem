package cn.edu.zjut.fts.controller;
import cn.edu.zjut.fts.entity.Delegate;
import cn.edu.zjut.fts.entity.Position;
import cn.edu.zjut.fts.entity.Transaction;
import cn.edu.zjut.fts.mapper.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.sql.Timestamp;

@Api(tags = "买卖模块")
@RestController
public class BuyOrSellController {

    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private DelegateMapper delegateMapper;
    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private aMapper aMapper;
    @Autowired
    private a_futuresMapper a_futuresMapper;
    @Autowired
    private ag_futuresMapper ag_futuresMapper;
    @Autowired
    private al_futuresMapper al_futuresMapper;
    @Autowired
    private alMapper alMapper;
    @Autowired
    private agMapper agMapper;

    @PostMapping("/BuyOrSell")
    public void buyOrSell(@RequestBody  Position request) {
        System.out.println(request);
        String id = request.getId();
        String username = request.getUsername();
        String bs = request.getBs();
        int pos = request.getPos();
        int F_price = request.getF_Price();
        System.out.println(F_price);


        //0.获取id种类第Q_id行的Q_price数据
        int Q_id =0;
        int Q_price =0;
        long t_date=0;
        Time t_time = null;
        if(id.equals("a")){Q_id = aMapper.geta_Last_Read_PositionId();Q_price =a_futuresMapper. selecta_PriceByLastReadPositionId(Q_id);t_date=a_futuresMapper.selecta_dateByLastReadPositionId(Q_id);t_time=a_futuresMapper.selecta_timeByLastReadPositionId(Q_id);}
        if(id.equals("ag")){Q_id = agMapper.getag_Last_Read_PositionId();Q_price =ag_futuresMapper.selectag_PriceByLastReadPositionId(Q_id);t_date=ag_futuresMapper.selectag_dateByLastReadPositionId(Q_id);t_time=ag_futuresMapper.selectag_timeByLastReadPositionId(Q_id);}
        if(id.equals("al")){Q_id = alMapper.getal_Last_Read_PositionId();Q_price =al_futuresMapper. selectal_PriceByLastReadPositionId(Q_id);t_date=al_futuresMapper.selectal_dateByLastReadPositionId(Q_id);t_time=al_futuresMapper.selectal_timeByLastReadPositionId(Q_id);}
        // 将long类型的日期转换为java.sql.Date
        Date switce_date = new Date(t_date);
        java.sql.Date sqlDate = new java.sql.Date(switce_date.getTime());
        // 将Time类型的时间转换为java.sql.Time
        java.sql.Time sqlTime = new java.sql.Time(t_time.getTime());
        // 使用java.sql.Timestamp将日期和时间组合在一起
        Timestamp combinedDateTime = new Timestamp(sqlDate.getTime() + sqlTime.getTime());
        request.setN_Time(combinedDateTime);
        System.out.println("Q_id:"+Q_id);
        System.out.println("Q_price:"+Q_price);


        // 1. 插入委托数据
        String f_tran ="";
        Delegate delegate = new Delegate();
        delegate.setId(id);
        delegate.setAtt(bs+"开");
        delegate.setUsername(username);
        delegate.setD_Price(F_price);
        delegate.setNum(pos);
        delegate.setD_time(new Timestamp(request.getN_Time().getTime()));
        int f_decide =0;
        if(bs.equals("买")&F_price<Q_price){delegate.setStatus("已委");f_decide =1;}
        if(bs.equals("卖")&F_price>Q_price){delegate.setStatus("已委");f_decide=1;}
        if(f_decide==0){delegate.setStatus("已成");f_tran =delegate.getStatus();}
        delegateMapper.insertDelegate(delegate);
        System.out.println("委托数据"+delegate);
        if(f_tran.equals("已成")) {
            // 2. 插入成交记录数据
            Transaction transaction = new Transaction();
            transaction.setId(id);
            transaction.setUsername(username);
            transaction.setBs(bs + "开");
            transaction.setNum(pos);
            transaction.setPrice(F_price);
            transaction.setPremium(25);
            transaction.setPro(F_price - 25);
            transaction.setTime(new Timestamp(request.getN_Time().getTime()));
            transactionMapper.insertTransaction(transaction);
            System.out.println("成交记录:"+transaction);

            // 3. 插入持仓数据
            Position position =new Position();
            position.setId(id);
            position.setUsername(username);
            position.setBs(bs);
            position.setPos(pos);
            position.setF_Price(F_price);
            position.setN_Time(request.getN_Time());
            position.setC_Pro(0);
            position.setR_Pro(0.00);
            position.setN_Price(position.getF_Price());

            // 处理 Delivery 的逻辑
            LocalDateTime nTime = request.getN_Time().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
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
            System.out.println("持仓数据"+position);
        }
    }
}
