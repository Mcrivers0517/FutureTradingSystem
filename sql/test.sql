-- 示例插入User表数据
INSERT INTO User (username, password, avatarUrl) VALUES
('user1', 'password1', 'avatar1.jpg'),
('user2', 'password2', 'avatar2.jpg');

-- 示例插入PositionTable表数据
INSERT INTO PositionTable (id, username, BS, POS, C_pro, R_pro, F_price, N_price, N_time) VALUES
('ABC123', 'user1', 'Buy', 100, 50, 0.25, 200, 210, '2023-01-01 12:00:00'),
('XYZ789', 'user2', 'Sell', 75, -30, -0.15, 150, 140, '2023-01-02 14:30:00');

-- 示例插入DelegateTable表数据
INSERT INTO DelegateTable (id, username, att, status, num, price, time) VALUES
('ABC123', 'user1', 'Buy', 1, 50, 205, '2023-01-03 10:45:00'),
('XYZ789', 'user2', 'Sell', 0, 25, 145, '2023-01-04 08:15:00');
