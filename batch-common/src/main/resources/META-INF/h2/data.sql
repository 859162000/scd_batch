set schema fcore_part_0;

--acct





--------------------------------------------bid

insert into bid_project values(
1, 'project_name', 'project_code2', 'work_no', '1',
50000, 0.21, 1, 100, 100,
10000, 101.32, CURRENT_DATE, CURRENT_DATE, CURRENT_DATE,
CURRENT_DATE, 12, '1', '1', '01',
'1', 1, '0', 'borrowerId1', '01',
'desc', CURRENT_DATE, CURRENT_DATE, 1, 5,
10100, 20100, 1000, 'use');


insert into bid_repay_plan values(1, 1, CURRENT_DATE, 10223, 120,
10, 3, 13, 1, 'project_code',
1, CURRENT_DATE, '1');


INSERT INTO `bid_borrower_relation` VALUES
('1', 'project_code', '1', '123456', '12345678',null, '你大爷', 'daye', '北京市朝阳区', '2016-09-08 12:10:17','2016-09-08 12:10:17', '1'),
('3', 'huadafafafa12345', '4', '123456', '12345678',null, '你大爷', 'daye', '北京市朝阳区', '2016-09-08 19:35:02','2016-09-08 19:35:02', '1'),
('5', 'XM16092021000002', '12', '12321312', '12321321', '123', '123', '12321', null, '2016-09-21 12:01:23', '2016-09-21 12:03:28', '0'),
('6', 'XM16092021000002', '12', '12321312', '12321321', '123', '123', '12321', null, '2016-09-21 12:03:28', '2016-09-21 13:55:40', '0'),
('7', 'XM16092021000002', '12', '12321312', '12321321', '123', '123', '12321', null, '2016-09-21 13:55:40', '2016-09-21 13:56:33', '0'),
('8', 'XM16092021000002', '12', '12321312', '12321321', '123', '123', '12321', null, '2016-09-21 13:56:33', '2016-09-21 14:09:10', '0'),
('9', 'XM16092021000002', '12', '12321312', '12321321', '123', '123', '12321', null, '2016-09-21 14:09:10', '2016-09-21 14:09:29', '0'),
('10', 'XM16092021000002', '1474441672919322962', '12321312', '12321321', '123', '123', '12321', null, '2016-09-21 14:09:29', '2016-09-21 14:09:29', '1'),
('13', 'XM16092119000001', '1474441672919322962', '1', '1', '1', '1', '1', null, '2016-09-21 20:05:14', '2016-09-21 20:05:14', '1'),
('14', 'XM16092313490001', '1474441672919322962', 'hetong201609160001', 'yuanshi201609160001', '抵押借款', '中国火箭', '中国火箭', '中国北京', '2016-09-23 13:49:21', '2016-09-23 13:49:21', '1'),
('15', 'XM16092716050001', '1474441672919322962', '123213123', 'jiekht001', '借款合同', '王五', '张三', null, '2016-09-27 16:05:21', '2016-09-27 16:05:39', '0'),
('16', 'XM16092716050001', '1474441672919322962', '123213123', 'jiekht001', '借款合同', '王五', '张三', null, '2016-09-27 16:05:39', '2016-09-27 16:45:48', '0'),
('17', 'XM16092716050001', '1474441672919322962', '123213123', 'jiekht001', '借款合同', '王五', '张三', null, '2016-09-27 16:45:48', '2016-09-27 18:07:22', '0'),
('18', 'XM16092716050001', '1474441672919322962', '123213123', 'jiekht001', '借款合同', '王五', '张三', null, '2016-09-27 18:07:22', '2016-09-27 18:41:36', '0'),
('19', 'XM16092716050001', '1474441672919322962', '123213123', 'jiekht001', '借款合同', '王五', '张三', null, '2016-09-27 18:41:36', '2016-09-27 18:46:32', '0'),
;


INSERT INTO `bid_borrower` VALUES
('1', '1000', '王测测', '01', '110100', '00', '620324198012224632', '15091837290',null, null, '12', '111100.00', '幼儿园', '0', '北京市朝阳区', '333@qq.com', '2016-09-06 21:04:22', '2016-09-06 21:04:22', '1'),
('4', '4', '秒钱', '02', '0001002003', '00', '138003198909930987', '15611894567', null, null, '12', '11119900.00', '本科', '0', '北京市朝阳区', '444@qq.com', '2016-09-08 19:33:59', '2016-09-08 19:33:59', '1'),
('5', '1', '22220', '111', '30000', '0', '99999', '11114', null, '111', '111', '0.00', '大学', '49', '福建省', '777@qq.com', '2016-09-11 22:13:55', '2016-09-11 22:13:55', '1'),
('6', '10', '黄飞鸿', '01', '11010088', '00', '138003198909930777', '138001387777', null, null, '12', '22666600.00', '高中', '3', '北京市朝阳区', '222@qq.com', '2016-09-19 14:11:21', '2016-09-19 14:11:21', '1'),
('7', '90', '黄飞鸿', '01', '11010088', '00', '138003198909930777', '138001387777', null, '秒錢', '12', '22666600.00', '高中', '3', '厦门市湖里区五缘湾泗水道597号海富中心A栋16楼C单元秒钱', '222@qq.com', '2016-09-19 14:35:57', '2016-09-19 14:42:25', '0'),
('14', '91', '黄飞鸿', '01', '11010088', '00', '138003198909930777', '138001387777', '12345678664', '秒錢', '12', '22666600.00', '高中', '3', '北京市朝阳区', '222@qq.com', '2016-09-19 14:57:00', '2016-09-19 14:59:24', '0')
;


insert into bid_creditor_relation values(
1, 'order_seq_no', 'project_code', 'product_code2', 'previous_seq_no',
'order_flow_no', 'previous_flow_no', 'buyer_uid', 'seller_uid', 'borrower_id',
10000, '1', CURRENT_TIMESTAMP,3000, 3000,
2122, 111,3333, 4444,2222,
111,111,111,3,1,
CURRENT_TIMESTAMP, 1, 'origin_flow_no', 1, CURRENT_DATE,
10, CURRENT_DATE
);

insert into bid_creditor_relation values(
1, 'order_seq_no', 'project_code', 'product_code3', 'previous_seq_no',
'order_flow_no', 'previous_flow_no', 'buyer_uid', 'seller_uid', 'borrower_id',
10000, '1', CURRENT_TIMESTAMP,3000, 3000,
2122, 111,3333, 4444,2222,
111,111,111,3,1,
CURRENT_TIMESTAMP, 1, 'origin_flow_no', 1, CURRENT_DATE,
10, CURRENT_DATE
);

INSERT INTO `bid_product` VALUES(
'337', 'product_code2', '转让CP16092916530000000000001', '102', '2',
'2016-09-29 16:36:27', '2017-02-28 16:36:27', 2,'项目融资抵押', '5',
'01', '2017-02-28 16:36:27', 'MB', '01', '10000.00',
'100.00', '10000.00', '6.00', '2017-02-28 16:36:27', '100.00',
'0.00', '6.50', 1, '2016-08-29 16:36:27', '0',
'0.00', '201600929', '0.00', 'create', '100.00',
'2', '110.00', null, null, '2017-02-28 16:36:27',
'04', '12', '0', '6.50');


INSERT INTO `bid_product` VALUES(
'337', 'product_code3', '转让CP16092916530000000000001', '102', '2',
'2016-09-29 16:36:27', '2017-02-28 16:36:27', 3,'项目融资抵押', '5',
'01', '2017-02-28 16:36:27', 'MB', '01', '10000.00',
'100.00', '10000.00', '6.00', '2017-02-28 16:36:27', '100.00',
'0.00', '6.50', 1, '2016-08-29 16:36:27', '0',
'0.00', '201600929', '0.00', 'create', '100.00',
'2', '110.00', null, null, '2017-02-28 16:36:27',
'04', '12', '0', '6.50');




-----------------------------------------executor
insert into DAY_CUT_INFO values(
1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_DATE
);



-------------------------------------------promotion
insert into promotion_coupons values(1,'name','alias','0','1000','description','0.02',10,1,CURRENT_DATE,CURRENT_DATE,'create_by','update_by');
insert into promotion_coupons_use_records values(100, 1, 'user_id', '105', '66', 0, CURRENT_DATE, CURRENT_DATE);





--------------------------------------statistics
insert into s_projectlimit values(
1, 'project_code', 'project_name', 1000000, 200000,
800000, 10000, 11000,200010, 101,
10002, 1, CURRENT_DATE, CURRENT_DATE);

insert into s_fixproductduestat values(
1, CURRENT_DATE, 1000,2000,3000,
4000,5000, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into s_expenditurestat values(
1, CURRENT_TIME,1,10003,1,
CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
);

insert into s_borrowerrepayplan values(
1,CURRENT_DATE, 100, 'BORROWER_NAME', 'PROJECT_CODE',
'PROJECT_NAME', 1, 100,332,322,
333,222,222,0,CURRENT_TIMESTAMP,
CURRENT_TIMESTAMP
);

insert into s_assetsstat values(
1, CURRENT_DATE,2000, 300,4000,
111,333,0,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
);

insert into s_fundstat values(
1, 10, 100,CURRENT_DATE, 0,
CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
);



-------------------------------------------trade
insert into recharge_l values(
1, 'seq_no', 'uid', 'user_cust_id', 1000,
103, '0', 'order_id', '20161011', 'gateid',
'bank_id', 'c', 'return_url', 'open_acct_id', 'id_card',
'1', 12, 'fee_cust_id', 'fee', CURRENT_TIMESTAMP,
CURRENT_TIMESTAMP, CURRENT_DATE, CURRENT_TIMESTAMP, 'N', '1',
'N', 'N');

insert into withdraw_l values(
1, 'seq_no', 'uid', 'user_cust_id', 'remark',
'1', '001','response_desc',4000, '1',
1000, 'order_id', 'open_acct_id', 'bank_id', 100,
'fee_cust_id', 'fee_a', 330, 'fee_id', 'return_url',
'bg_return_url', CURRENT_DATE, CURRENT_TIMESTAMP, '1'
);

insert into user_balance values(
1, 'uid', '1', 1000, 10,
10, 32, 3,2,4,
43,4,55,33,22,
44,22,22222,1111,11111,
32222,33333,33333,10,CURRENT_TIMESTAMP
);
