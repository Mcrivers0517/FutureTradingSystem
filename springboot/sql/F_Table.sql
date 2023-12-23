DROP TABLE IF EXISTS a_Futures,ag_Futures,al_Futures,a_last_read_position,ag_last_read_position,al_last_read_position;
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
