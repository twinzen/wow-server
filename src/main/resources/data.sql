INSERT INTO TB_USER
(USER_NAME,
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
 UPDATE_DATE_TIME)
VALUES ('user1', 'user1@mail.com', 'password', 'User 1', '2020-01-20', 2, 'assetSizeRange', '1970-05-05', 'high', 'YES',
        'MARRIED', '10000', 'user intro', '2020-09-01 12:00:00', '2020-09-01 14:00:00'),
       ('user2', 'user2@mail.com', 'password', 'User 2', '2020-01-22', 2, 'assetSizeRange', '1970-02-02', 'high', 'YES',
        'MARRIED', '10000', 'user intro', '2020-09-02 12:00:00', '2020-09-02 14:02:00'),
       ('user3', 'user3@mail.com', 'password', 'User 3', '2020-01-23', 2, 'assetSizeRange', '1970-03-03', 'high', 'YES',
        'MARRIED', '10000', 'user intro', '2020-09-03 12:00:00', '2020-09-03 14:03:00'),
       ('user4', 'user4@mail.com', 'password', 'User 4', '2020-01-24', 2, 'assetSizeRange', '1970-04-04', 'high', 'YES',
        'MARRIED', '10000', 'user intro', '2020-09-04 12:00:00', '2020-09-04 14:04:00'),
       ('user5', 'user5@mail.com', 'password', 'User 5', '2020-01-25', 2, 'assetSizeRange', '1970-05-05', 'high', 'YES',
        'MARRIED', '10000', 'user intro', '2020-09-05 12:00:00', '2020-09-05 14:05:00'),
       ('user6', 'user6@mail.com', 'password', 'User 6', '2020-01-26', 2, 'assetSizeRange', '1970-06-06', 'high', 'YES',
        'MARRIED', '10000', 'user intro', '2020-09-06 12:00:00', '2020-09-06 14:06:00'),
       ('user7', 'user7@mail.com', 'password', 'User 7', '2020-01-27', 2, 'assetSizeRange', '1970-07-07', 'high', 'YES',
        'MARRIED', '10000', 'user intro', '2020-09-07 12:00:00', '2020-09-07 14:07:00'),
       ('user8', 'user8@mail.com', 'password', 'User 8', '2020-01-28', 2, 'assetSizeRange', '1970-08-08', 'high', 'YES',
        'MARRIED', '10000', 'user intro', '2020-09-08 12:00:00', '2020-09-08 14:08:00')
;


INSERT INTO TB_FOLLOW
(FOLLOWER_USER_ID,
 FOLLOWING_USER_ID,
 CREATION_DATE_TIME,
 UPDATE_DATE_TIME)
VALUES (1, 2, '2020-09-01 14:08:00', '2020-09-01 14:08:00'),
       (1, 3, '2020-09-02 14:08:00', '2020-09-02 14:08:00'),
       (1, 8, '2020-09-03 14:08:00', '2020-09-03 14:08:00'),
       (3, 1, '2020-09-04 14:08:00', '2020-09-04 14:08:00'),
       (3, 5, '2020-09-05 14:08:00', '2020-09-05 14:08:00'),
       (3, 6, '2020-09-06 14:08:00', '2020-09-06 14:08:00'),
       (6, 1, '2020-09-07 14:08:00', '2020-09-07 14:08:00'),
       (3, 2, '2020-09-08 14:08:00', '2020-09-08 14:08:00'),
       (6, 5, '2020-09-09 14:08:00', '2020-09-09 14:08:00'),
       (6, 4, '2020-09-10 14:08:00', '2020-09-10 14:08:00'),
       (2, 8, '2020-09-11 14:08:00', '2020-09-11 14:08:00'),
       (4, 1, '2020-09-12 14:08:00', '2020-09-12 14:08:00')
;



INSERT INTO TB_PRODUCT
(PRODUCT_CODE,
 PRODUCT_NAME)
VALUES ('PRDCD01', 'PRODUCT 01'),
       ('PRDCD02', 'PRODUCT 02'),
       ('PRDCD03', 'PRODUCT 03'),
       ('PRDCD04', 'PRODUCT 04'),
       ('PRDCD05', 'PRODUCT 05'),
       ('PRDCD06', 'PRODUCT 06'),
       ('PRDCD07', 'PRODUCT 07'),
       ('PRDCD08', 'PRODUCT 08'),
       ('PRDCD09', 'PRODUCT 09'),
       ('PRDCD10', 'PRODUCT 10'),
       ('PRDCD11', 'PRODUCT 11'),
       ('PRDCD12', 'PRODUCT 12')
;

INSERT INTO TB_WATCH_ITEM
(USER_ID,
 PRODUCT_ID,
 CREATION_DATE_TIME)
VALUES (1, 2, '2020-09-01 01:01:00'),
       (1, 5, '2020-09-02 02:02:00'),
       (2, 2, '2020-09-03 03:03:00'),
       (3, 8, '2020-09-04 04:04:00'),
       (4, 7, '2020-09-05 05:05:00'),
       (4, 11, '2020-09-06 06:06:00'),
       (6, 2, '2020-09-07 07:07:00'),
       (6, 8, '2020-09-08 08:08:00'),
       (6, 9, '2020-09-09 09:09:00'),
       (6, 12, '2020-09-10 10:10:00'),
       (7, 1, '2020-09-11 11:11:00'),
       (10, 2, '2020-09-12 12:12:00')
;

