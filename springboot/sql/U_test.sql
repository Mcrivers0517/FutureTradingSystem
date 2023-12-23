-- 插入用户数据
INSERT INTO User (username, password, avatarUrl) VALUES
('user1', 'password1', 'avatar1.jpg'),
('user2', 'password2', 'avatar2.jpg'),
('user3', 'password3', 'avatar3.jpg');

-- 插入持仓数据
INSERT INTO PositionTable (id, username, bs, POS, C_pro, R_pro, F_price, N_price, N_time, Delivery) VALUES
('A001', 'user1', 'Buy', 100, 50, 0.05, 100, 110, '2023-01-01 12:00:00', '2023-02-01 12:00:00'),
 ('B001', 'user2', 'Sell', 50, -20, -0.02, 150, 140, '2023-01-02 14:30:00', '2023-02-02 14:30:00'),
('C001', 'user3', 'Buy', 75, 30, 0.03, 200, 210, '2023-01-03 10:45:00', '2023-02-03 10:45:00');

-- 插入成交记录数据
INSERT INTO transactiontable (id, username, bs, price, premium, pro,time) VALUES
('A001', 'user1', 'Buy', 110, 5, 1,'2023-01-01 12:05:00'),
('B001', 'user2', 'Sell', 140, 3,1, '2023-01-02 14:35:00'),
('C001', 'user3', 'Buy', 210, 8,1, '2023-01-03 10:50:00');

-- 插入委托记录数据
INSERT INTO DelegateTable (id, username, att, status, num, D_price, D_time) VALUES
('A001', 'user1', 'Buy', 1, 50, 105, '2023-01-01 11:55:00'),
('B001', 'user2', 'Sell', 0, 30, 145, '2023-01-02 14:25:00'),
('C001', 'user3', 'Buy', 1, 25, 205, '2023-01-03 10:40:00');
