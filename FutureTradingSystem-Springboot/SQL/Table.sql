DROP TABLE IF EXISTS user,Position,Delegate;

#建立用户表
CREATE TABLE IF NOT EXISTS User(
    /*编号*/id  INT PRIMARY KEY  NOT NULL AUTO_INCREMENT,
    /*用户名*/username VARCHAR(100) UNIQUE,
    /*密码*/password VARCHAR(100) NOT NULL
);

#建立持仓表
CREATE TABLE IF NOT EXISTS Position(
    /*品种*/id VARCHAR(10) PRIMARY KEY,
    /*持仓人*/username VARCHAR(100) UNIQUE,
    /*买或卖*/BS VARCHAR(10),
    /*当前持仓量*/POS INT(1000),
    /*浮动盈亏*/C_pro INT(1000),
    /*盈亏比率*/R_pro DECIMAL(4,2),
    /*开仓价格*/F_price INT(1000),
    /*现在价格*/N_price INT(1000),
    /*成交时间*/N_time DATETIME,
    /*username作为外键*/FOREIGN KEY Position(username) REFERENCES User(username)
);

#建立委托记录表
CREATE TABLE IF NOT EXISTS Delegate(
    /*品种*/id VARCHAR(10) PRIMARY KEY,
    /*持仓人*/username VARCHAR(100) UNIQUE,
    /*属性*/att VARCHAR(10),
    /*状态*/status TINYINT(1),
    /*委托数量*/num INT(100),
    /*委托价格*/price INT(1000),
    /*委托时间*/time INT(1000),
    /*username作为外键*/FOREIGN KEY Delegate(username) REFERENCES User(username)
);