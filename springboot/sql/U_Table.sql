DROP TABLE IF EXISTS User,PositionTable,DelegateTable,TransactionTable;

#建立用户表
CREATE TABLE IF NOT EXISTS User(
    /*编号*/id  INT PRIMARY KEY  NOT NULL AUTO_INCREMENT,
    /*用户名*/username VARCHAR(100) UNIQUE,
    /*密码*/password VARCHAR(100) NOT NULL,
    /*头像地址*/avatarUrl VARCHAR(100)
);

#建立持仓表
CREATE TABLE IF NOT EXISTS PositionTable(
    /*品种*/id VARCHAR(10),
    /*持仓人*/username VARCHAR(100) ,
    /*买或卖*/bs VARCHAR(10),
    /*当前持仓量*/POS INT,
    /*浮动盈亏*/C_pro INT,
    /*盈亏比率*/R_pro DECIMAL(4,2),
    /*开仓价格*/F_price INT,
    /*现在价格*/N_price INT,
    /*成交时间*/N_time DATETIME,
    /*交割日期*/Delivery DATETIME,
    /*username作为外键*/FOREIGN KEY PositionTable(username) REFERENCES User(username)
);

#建立成交记录表
CREATE TABLE IF NOT EXISTS TransactionTable(
    /*品种*/id VARCHAR(10),
    /*持仓人*/username VARCHAR(100) ,
    /*买或卖,开或平*/bs VARCHAR(10),
    /*成交价格*/price INT,
    /*手续费*/premium INT,
    /*利润*/pro INT,
    /*成交时间*/time DATETIME
);

#建立委托记录表
CREATE TABLE IF NOT EXISTS DelegateTable(
    /*品种*/id VARCHAR(10),
    /*持仓人*/username VARCHAR(100) ,
    /*属性*/att VARCHAR(10),
    /*状态*/status TINYINT(1),
    /*委托数量*/num INT,
    /*委托价格*/D_price INT,
    /*委托时间*/D_time DATETIME
);
