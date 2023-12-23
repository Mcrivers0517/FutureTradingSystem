DROP TABLE IF EXISTS User,PositionTable,DelegateTable,a_Futures,ag_Futures,al_Futures,a_last_read_position,ag_last_read_position,al_last_read_position;

#建立用户表
CREATE TABLE IF NOT EXISTS User(
    /*编号*/id  INT PRIMARY KEY  NOT NULL AUTO_INCREMENT,
    /*用户名*/username VARCHAR(100) UNIQUE,
    /*密码*/password VARCHAR(100) NOT NULL,
    /*头像地址*/avatarUrl VARCHAR(100)
);

#建立持仓表
CREATE TABLE IF NOT EXISTS PositionTable(
    /*品种*/id VARCHAR(10) PRIMARY KEY,
    /*持仓人*/username VARCHAR(100) UNIQUE,
    /*买或卖*/BS VARCHAR(10),
    /*当前持仓量*/POS INT,
    /*浮动盈亏*/C_pro INT,
    /*盈亏比率*/R_pro DECIMAL(4,2),
    /*开仓价格*/F_price INT,
    /*现在价格*/N_price INT,
    /*成交时间*/N_time DATETIME,
    /*交割日期*/Delivery DATETIME,
    /*username作为外键*/FOREIGN KEY PositionTable(username) REFERENCES User(username)
);


#建立委托记录表
CREATE TABLE IF NOT EXISTS DelegateTable(
    /*品种*/id VARCHAR(10) PRIMARY KEY,
    /*持仓人*/username VARCHAR(100) UNIQUE,
    /*属性*/att VARCHAR(10),
    /*状态*/status TINYINT(1),
    /*委托数量*/num INT,
    /*委托价格*/price INT,
    /*委托时间*/time DATETIME,
    /*username作为外键*/FOREIGN KEY (username) REFERENCES User(username)
);

#建立豆一2009期货表单
CREATE TABLE IF NOT EXISTS a_Futures(
    /*日期*/ date INT,
    /*时间*/ time TIME,
    /*价格*/ price INT,
    /*成交量*/ Volume INT

);

#记录豆一2009期货表单最后一次读取的数据
CREATE TABLE a_last_read_position (
    a_last_position_id INT
);
INSERT into a_last_read_position(a_last_position_id)
value (1);

#建立银2008期货表单
CREATE TABLE IF NOT EXISTS ag_Futures(
    /*日期*/ date INT,
    /*时间*/ time TIME,
    /*价格*/ price INT,
    /*成交量*/ Volume INT

);

#记录银2008期货表单最后一次读取的数据
CREATE TABLE ag_last_read_position (
    ag_last_position_id INT
);
INSERT into ag_last_read_position(ag_last_position_id)
value (1);

#建立铝2008期货表单
CREATE TABLE IF NOT EXISTS al_Futures(
    /*日期*/ date INT,
    /*时间*/ time TIME,
    /*价格*/ price INT,
    /*成交量*/ Volume INT
);

#记录铝2008期货表单最后一次读取的数据
CREATE TABLE al_last_read_position (
    al_last_position_id INT
);
INSERT into al_last_read_position(al_last_position_id)
value (1);
