INSERT INTO TB_USER
(
USER_ID,
USER_NAME,
EMAIL,
PASSWORD,
DISPLAY_NAME,
FIRST_TRADE_DATE,
RISK_PROFILE,
ASSET_SIZE_RANGE,
DATE_OF_BIRTH,
EDUCATION_LEVEL,
HAVE_CHILD,
MARITAL_STATUS,
MONTHLY_INCOME_RANGE,
INTRODUCTION,
CREATION_DATE_TIME,
UPDATE_DATE_TIME
)
VALUES
(1,'user1' , 'user1@mail.com', 'password' , 'User 1' , '2020-01-20', 2 , 'assetSizeRange' , '1970-05-05', 'high', 'YES', 'MARRIED','10000', 'user intro', '2020-09-01 12:00:00', '2020-09-01 14:00:00'),
(2,'user2' , 'user2@mail.com', 'password' , 'User 2' , '2020-01-22', 2 , 'assetSizeRange' , '1970-02-02', 'high', 'YES', 'MARRIED','10000', 'user intro', '2020-09-02 12:00:00', '2020-09-02 14:02:00'),
(3,'user3' , 'user3@mail.com', 'password' , 'User 3' , '2020-01-23', 2 , 'assetSizeRange' , '1970-03-03', 'high', 'YES', 'MARRIED','10000', 'user intro', '2020-09-03 12:00:00', '2020-09-03 14:03:00'),
(4,'user4' , 'user4@mail.com', 'password' , 'User 4' , '2020-01-24', 2 , 'assetSizeRange' , '1970-04-04', 'high', 'YES', 'MARRIED','10000', 'user intro', '2020-09-04 12:00:00', '2020-09-04 14:04:00'),
(5,'user5' , 'user5@mail.com', 'password' , 'User 5' , '2020-01-25', 2 , 'assetSizeRange' , '1970-05-05', 'high', 'YES', 'MARRIED','10000', 'user intro', '2020-09-05 12:00:00', '2020-09-05 14:05:00'),
(6,'user6' , 'user6@mail.com', 'password' , 'User 6' , '2020-01-26', 2 , 'assetSizeRange' , '1970-06-06', 'high', 'YES', 'MARRIED','10000', 'user intro', '2020-09-06 12:00:00', '2020-09-06 14:06:00'),
(7,'user7' , 'user7@mail.com', 'password' , 'User 7' , '2020-01-27', 2 , 'assetSizeRange' , '1970-07-07', 'high', 'YES', 'MARRIED','10000', 'user intro', '2020-09-07 12:00:00', '2020-09-07 14:07:00'),
(8,'user8' , 'user8@mail.com', 'password' , 'User 8' , '2020-01-28', 2 , 'assetSizeRange' , '1970-08-08', 'high', 'YES', 'MARRIED','10000', 'user intro', '2020-09-08 12:00:00', '2020-09-08 14:08:00')
;


INSERT INTO TB_FOLLOW
(
FOLLOW_ID,
CREATION_DATE_TIME,
USER_ID,
FOLLOWER_ID,
UPDATE_DATE_TIME
)
VALUES
(1  , '2020-09-01 14:08:00' , 1, 2 , '2020-09-01 14:08:00'),
(2  , '2020-09-02 14:08:00' , 1, 3 , '2020-09-02 14:08:00'),
(3  , '2020-09-03 14:08:00' , 1, 8 , '2020-09-03 14:08:00'),
(4  , '2020-09-04 14:08:00' , 3, 1 , '2020-09-04 14:08:00'),
(5  , '2020-09-05 14:08:00' , 3, 5 , '2020-09-05 14:08:00'),
(6  , '2020-09-06 14:08:00' , 3, 6 , '2020-09-06 14:08:00'),
(7  , '2020-09-07 14:08:00' , 6, 1 , '2020-09-07 14:08:00'),
(8  , '2020-09-08 14:08:00' , 3, 2 , '2020-09-08 14:08:00'),
(9  , '2020-09-09 14:08:00' , 6, 5 , '2020-09-09 14:08:00'),
(10 , '2020-09-10 14:08:00' , 6, 4 , '2020-09-10 14:08:00'),
(11 , '2020-09-11 14:08:00' , 2, 8 , '2020-09-11 14:08:00'),
(12 , '2020-09-12 14:08:00' , 4, 1 , '2020-09-12 14:08:00')

;
