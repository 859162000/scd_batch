use fcore_part_0;


--acct

--用户累计收益统计
DROP TABLE IF EXISTS `user_accumulative_profit`;
CREATE TABLE `s_user_accumulative_profit` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `uid` varchar(32) NOT NULL DEFAULT '' COMMENT '用户id',
  `total_profit` decimal(14,6) NOT NULL DEFAULT '0.000000' COMMENT '累计收益',
  `current_invest_profit` decimal(14,6) NOT NULL DEFAULT '0.000000' COMMENT '秒钱宝在投收益',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '记录生成时间',
  `update_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '记录最近更改时间',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--用户每日收益统计
DROP TABLE IF EXISTS `user_daily_profit`;
CREATE TABLE `s_user_daily_profit` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `uid` varchar(32) NOT NULL DEFAULT '' COMMENT '用户id',
  `date` varchar(16) NOT NULL DEFAULT '0000-00-00' COMMENT '收益日期',
  `profit` decimal(14,6) NOT NULL DEFAULT '0.000000' COMMENT '今日收益',
  `current_profit` decimal(14,6) NOT NULL DEFAULT '0.000000' COMMENT '秒钱宝收益',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '记录生成时间',
  `update_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '记录最近更改时间',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



--bid
DROP TABLE IF EXISTS bid_project;
CREATE TABLE bid_project (
  id               BIGINT(20) primary key NOT NULL AUTO_INCREMENT,
  project_name     VARCHAR(30)         NOT NULL,
  project_code     VARCHAR(32)         NOT NULL,
  work_no     VARCHAR(32)         NOT NULL,
  bid_type         VARCHAR(3)          NOT NULL,

  bid_amount       DECIMAL(14, 2)      NOT NULL,
  year_rate        DECIMAL(4, 2)       NOT NULL,
  min_invest_count    SMALLINT(6)         NOT NULL,
  unit_amount   DECIMAL(14, 2)      NOT NULL,
  min_invest_amount   DECIMAL(14, 2)      NOT NULL,

  max_invest_amount   DECIMAL(14, 2)      NOT NULL,
  total_interest   DECIMAL(14, 2)      NOT NULL,
  bid_start_time   DATETIME            NOT NULL,
  bid_end_time     DATETIME            NOT NULL,
  repay_time       DATETIME            NOT NULL,

  last_repay_time  DATETIME            NOT NULL,
  loan_period      SMALLINT(6)         NOT NULL,
  loan_period_unit CHAR(1)             NOT NULL,
  repay_type       CHAR(2)             NOT NULL,
  guarantee_type     CHAR(2)             NOT NULL,

  bid_product_type CHAR(4)             NOT NULL,
  is_pad_funded    tinyint(2)          NOT NULL,
  risk_ctrl_type   CHAR(4)             NOT NULL,
  borrower_id      VARCHAR(50)            NOT NULL,
  thd_audit_status     CHAR(4)             NOT NULL,

  thd_audit_desc       VARCHAR(256)        NOT NULL,
  create_time      DATETIME            NOT NULL,
  update_time      DATETIME            NOT NULL,
  data_status      tinyint(2)          NOT NULL,
  `status` tinyint(2) NOT NULL,

  invested_amount decimal(14, 2) NOT  NULL,
  frozen_amount decimal(14, 2) NOT  NULL,
  occupy_amount decimal(14, 2) NOT  NULL,
  loan_use varchar(180) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `bid_repay_plan`;
CREATE TABLE `bid_repay_plan` (
  `id` bigint(20) primary key auto_increment,
  `repay_term` int(8) NULL,
  `repay_date` date NULL,
  `repay_amount` decimal(14,2) NULL ,
  `repay_interest` decimal(14,2) NULL ,

  `current_debt_amount` decimal(14,2) NULL ,
  `current_vouch_amount` decimal(14,2) NULL ,
  `current_sum_amount` decimal(14,2) NULL ,
  `currency` tinyint(2) NULL ,
  `project_code` varchar(40) NULL ,

  `data_status` tinyint(2) NULL,
  `create_time` datetime NULL,
  `plan_id` varchar(40) NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `bid_credit_repay_real`;
CREATE TABLE `bid_credit_repay_real` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `project_plan_id` varchar(40) DEFAULT NULL COMMENT '项目还款计划Id',
  `repay_plan_id` varchar(40) DEFAULT NULL COMMENT '债权还款计划Id',
  `batch_no` varchar(40) DEFAULT NULL COMMENT '批量还款记录No',
  `repay_no` varchar(40) DEFAULT NULL COMMENT 'repayreal的编号用于对应借款人资金冻结',
  `repay_flow_no` varchar(40) DEFAULT NULL COMMENT '款编号,对应汇付的还款单号',
  `order_seq_no` varchar(40) DEFAULT NULL COMMENT '投资人认购订单编号',
  `order_flow_no` varchar(40) DEFAULT NULL COMMENT '交易流水号',
  `loan_flow_no` varchar(40) DEFAULT NULL COMMENT '放款单号',
  `project_code` varchar(40) DEFAULT NULL COMMENT '项目编号',
  `product_code` varchar(40) DEFAULT NULL COMMENT '产品编号',
  `invest_uid` varchar(40) DEFAULT NULL COMMENT '投资人Id',
  `borrower_id` varchar(40) DEFAULT NULL COMMENT '借款人Id',
  `repay_date` date DEFAULT NULL COMMENT '还款时间',
  `repay_amount` decimal(14,2) DEFAULT NULL COMMENT '还款本金',
  `repay_interest` decimal(14,2) DEFAULT NULL COMMENT '还款利息',
  `receipt_amount` decimal(14,2) DEFAULT NULL COMMENT '投资人收款本金',
  `receipt_interest` decimal(14,2) DEFAULT NULL COMMENT '投资人收款利息',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `data_status` tinyint(4) DEFAULT NULL COMMENT '数据状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `success_time` datetime DEFAULT NULL COMMENT '成功时间',
  `repay_type` varchar(10) DEFAULT NULL COMMENT '还款方式',
  `repay_time_type` varchar(10) DEFAULT NULL COMMENT '还款时间类型',
  `receipt_interest_rate` decimal(14,2) DEFAULT NULL COMMENT '收款利率',
  `repay_interest_rate` decimal(14,2) DEFAULT NULL COMMENT '还款利率',
  `repay_uid` varchar(40) DEFAULT NULL COMMENT '实际还款人UID 补息 垫资情况下 跟borrowerID不一致',
  `repay_amount_type` tinyint(4) DEFAULT NULL COMMENT '还款资金流水类型',
  `repay_user_type` varchar(10) DEFAULT NULL COMMENT '还款用户类型',
  `result_message` varchar(40) DEFAULT NULL COMMENT '汇付回调返回结果字符串',
  `interested_date` date DEFAULT NULL COMMENT '债权的时实际计息日期',
  `loan_date` date DEFAULT NULL COMMENT '放款时间',
  `credit_amount` decimal(14,2) DEFAULT NULL COMMENT '债权用来计算本期还息的本金',
  `trade_time` datetime DEFAULT NULL COMMENT '交易订单认购时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='债权的实际还款表';


DROP TABLE IF EXISTS `bid_loan`;
CREATE TABLE `bid_loan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `loan_flow_no` varchar(40) DEFAULT NULL COMMENT '放款单号,给汇付传的交易单号',
  `order_seq_no` varchar(40) DEFAULT NULL COMMENT '认购产品的交易单号',
  `project_code` varchar(40) DEFAULT NULL COMMENT '认购产品的交易单号',
  `product_code` varchar(40) DEFAULT NULL COMMENT '认购产品的交易单号',
  `product_type` tinyint(4) DEFAULT NULL COMMENT '认购产品的交易单号',
  `order_flow_no` varchar(40) DEFAULT NULL COMMENT '和汇付的交易时的单号,调用汇付时的subOrderId',
  `borrower_id` varchar(40) DEFAULT NULL COMMENT '借款人Id',
  `invest_uid` varchar(40) DEFAULT NULL COMMENT '投资人Id',
  `trade_amount` decimal(14,2) DEFAULT NULL COMMENT '认购的金额,即放款金额',
  `create_time` datetime DEFAULT NULL COMMENT '认购产品的交易单号',
  `order_time` datetime DEFAULT NULL COMMENT '认购时间',
  `success_time` datetime DEFAULT NULL COMMENT '放款成功时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `previous_status` tinyint(4) DEFAULT NULL,
  `data_status` tinyint(4) DEFAULT NULL COMMENT '数据状态',
  `third_result_code` varchar(40) DEFAULT NULL COMMENT '第三方返回编码',
  `third_result_msg` varchar(40) DEFAULT NULL COMMENT '第三方还款消息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `bid_borrower_relation`;
CREATE TABLE `bid_borrower_relation` (
  `id` bigint(20) NOT NULL  COMMENT '自增ID',
  `project_code` varchar(32) DEFAULT NULL COMMENT '项目编码',
  `borrower_id` varchar(50) DEFAULT NULL COMMENT '借款人Id',
  `loan_cont_id` varchar(32) DEFAULT NULL COMMENT '借款合同id',
  `old_cont_id` varchar(32) DEFAULT NULL COMMENT '原合同编号',
  `old_cont_name` varchar(32) DEFAULT NULL COMMENT '原合同名称',
  `creditor` varchar(100) DEFAULT NULL COMMENT '债权人简称',
  `creditor_name` varchar(100) DEFAULT NULL COMMENT '债权人名称',
  `creditor_address` varchar(100) DEFAULT NULL COMMENT '债权人地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `data_status` tinyint(1) DEFAULT NULL COMMENT '1：数据未删，0：数据已删'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `bid_borrower`;
CREATE TABLE `bid_borrower` (
  `id` bigint(20) NOT NULL COMMENT '自增ID',
  `borrower_id` varchar(50) DEFAULT NULL COMMENT '借款人ID',
  `name` varchar(80) DEFAULT NULL COMMENT '借款人名称',
  `type` char(4) DEFAULT NULL COMMENT '借款人类型：01-个人 02-企业',
  `license_code` varchar(40) DEFAULT NULL COMMENT '企业营业执照编码',
  `certificate_type` char(4) DEFAULT NULL COMMENT '借款人证件类型：00-身份证(type=01时必填)',
  `certificate_no` varchar(24) DEFAULT NULL COMMENT '证件编号(type=01时必填)',
  `mobile_phone` varchar(16) DEFAULT NULL COMMENT '手机号码',
  `telephone` varchar(16) DEFAULT NULL COMMENT '固定电话',
  `work_company` varchar(180) DEFAULT NULL COMMENT '借款人工作单位',
  `work_year` smallint(6) DEFAULT NULL COMMENT '借款人工作年限(单位：年)',
  `income` decimal(14,2) DEFAULT NULL COMMENT '借款人税后月收入',
  `education` varchar(8) DEFAULT NULL COMMENT '借款人学历',
  `marital_status` tinyint(4) DEFAULT NULL COMMENT '借款人婚姻状况:1-未婚2-已婚3-离异4-其他',
  `address` varchar(180) DEFAULT NULL COMMENT '借款人地址',
  `email` varchar(50) DEFAULT NULL COMMENT '借款人电子邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `data_status` tinyint(1) DEFAULT NULL COMMENT '1：数据未删，0：数据已删'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `bid_creditor_relation`;
CREATE TABLE `bid_creditor_relation` (
  `id` bigint(20) NOT NULL ,
  `order_seq_no` varchar(40) DEFAULT NULL COMMENT '认购交易单号',
  `project_code` varchar(40) DEFAULT NULL COMMENT '项目编号',
  `product_code` varchar(40) DEFAULT NULL COMMENT '产品编号',
  `previous_seq_no` varchar(40) DEFAULT NULL COMMENT '前一个交易单号',

  `order_flow_no` varchar(40) DEFAULT NULL COMMENT '交易流水号',
  `previous_flow_no` varchar(40) DEFAULT NULL COMMENT '前一个交易流水号',
  `buyer_uid` varchar(40) DEFAULT NULL COMMENT '购买者Id',
  `seller_uid` varchar(40) DEFAULT NULL COMMENT '出让者Id',
  `borrower_id` varchar(40) DEFAULT NULL COMMENT '借款人Id',

  `remain_amount` decimal(14,2) DEFAULT NULL COMMENT '剩余金额',
  `invest_type` tinyint(4) DEFAULT NULL COMMENT '投资类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `trade_amount` decimal(14,2) DEFAULT NULL COMMENT '交易金额',
  `repaid_amount` decimal(14,2) DEFAULT NULL COMMENT '借款人还款本金',

  `repaid_interest` decimal(14,2) DEFAULT NULL COMMENT '借款人还款利息',
  `receipted_amount` decimal(14,2) DEFAULT NULL COMMENT '已收本金(包含借款人还款+回购的本金)',
  `receipted_interest` decimal(14,2) DEFAULT NULL COMMENT '已收利息(包含借款人+回购的利息)',
  `buyback_amount` decimal(14,2) DEFAULT NULL COMMENT '回购本金',
  `buyback_interest` decimal(14,2) DEFAULT NULL COMMENT '回购利息',

  `transferred_amount` decimal(14,2) DEFAULT NULL COMMENT '已转出本金,转给其他投资人的本金',
  `remain_interest` decimal(14,2) DEFAULT NULL COMMENT '剩余可收利息',
  `total_interest` decimal(14,2) DEFAULT NULL COMMENT '总利息',
  `hand_times` tinyint(4) DEFAULT NULL COMMENT '第几手',
  `data_status` tinyint(4) DEFAULT NULL COMMENT '数据状态',

  `trade_time` datetime DEFAULT NULL COMMENT '交易时间',
  `product_type` tinyint(4) DEFAULT NULL COMMENT '产品类型',
  `origin_flow_no` varchar(40) DEFAULT NULL COMMENT '原始认购单号',
  `trade_type` tinyint(4) DEFAULT NULL COMMENT '交易类型',
  `interest_date` date DEFAULT NULL COMMENT '计息开始时间',

  `interest_rate` decimal(14,2) DEFAULT NULL COMMENT '利率',
  `expire_date` date DEFAULT NULL COMMENT '到期时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `bid_product`;
CREATE TABLE `bid_product` (
  `id` bigint(20) NOT NULL  COMMENT '主键',
  `product_code` varchar(40) NOT NULL COMMENT '产品编号',
  `product_name` varchar(100) NOT NULL COMMENT '产品名称',
  `pub_user_id` varchar(40) NOT NULL COMMENT '发布人id',
  `pub_user_type` tinyint(4) NOT NULL COMMENT '发布人类型',

  `start_time` datetime NOT NULL COMMENT '投标的开始时间',
  `end_time` datetime NOT NULL COMMENT '投标的结束时间',
  `product_type` tinyint(255) NOT NULL COMMENT '产品类型  活期 定期计划 定期赚',
  `description` varchar(255) NOT NULL COMMENT '产品描述',
  `status` tinyint(255) NOT NULL COMMENT '产品状态 待开标 已开标 等',

  `channel_code` varchar(20) NOT NULL COMMENT '适用渠道编号',
  `full_time` datetime NOT NULL COMMENT '满标时间',
  `interest_accrual_mode` varchar(10) NOT NULL COMMENT '计息模式',
  `bid_type` varchar(10) NOT NULL COMMENT '标的类型 应该废弃',
  `max_invest_amount` decimal(14,2) NOT NULL COMMENT '最大投资金额',

  `min_invest_amount` decimal(14,2) NOT NULL COMMENT '最小投资金额',
  `next_invest_amount` decimal(14,2) NOT NULL COMMENT '续投金额限制',
  `max_rate` decimal(4,2) NOT NULL COMMENT '最大年利率',
  `over_time` datetime NOT NULL COMMENT '结清时间',
  `bid_amount` decimal(14,2) NOT NULL COMMENT '产品额度',

  `invested_amount` decimal(14,2) NOT NULL,
  `year_rate` decimal(4,2) NOT NULL COMMENT '年利率',
  `data_status` tinyint(255) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `interest_start_day` int(255) NOT NULL COMMENT '从第几天开始计息 1 次日计息 0 为当日计息',

  `add_interest` decimal(4,2) NOT NULL COMMENT '加息',
  `present_integral` varchar(200) NOT NULL COMMENT '赠送积分',
  `frozen_amount` decimal(14,2) NOT NULL COMMENT '冻结金额',
  `pub_step_code` varchar(20) NOT NULL COMMENT '当前发布',
  `unit_amount` decimal(14,2) NOT NULL COMMENT '每份金额',

  `invest_type` tinyint(2) DEFAULT NULL,
  `bid_price` decimal(14,2) DEFAULT NULL COMMENT '产品转让交易价格',
  `remain_amount` decimal(14,2) DEFAULT NULL COMMENT '剩余可投资债权',
  `repay_amount` decimal(14,2) DEFAULT NULL COMMENT '已还本金',
  `expire_date` datetime DEFAULT NULL,

  `repay_type` varchar(100) DEFAULT NULL,
  `invested_count` int(11) DEFAULT '0' COMMENT '投资人次',
  `is_target` tinyint(4) DEFAULT NULL COMMENT '是否定向标产品',
  `product_rate` decimal(4,2) DEFAULT NULL COMMENT '产品发布利率'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



---bid


--promotion
DROP TABLE IF EXISTS promotion_coupons;
CREATE TABLE promotion_coupons (
  id             BIGINT NOT NULL,
  name           VARCHAR(32) NOT NULL,
  alias          VARCHAR(32) NOT NULL,
  type           VARCHAR(20) NOT NULL,
  value          VARCHAR(32) NOT NULL,

  description    VARCHAR(200) NOT NULL,
  used_ratio     VARCHAR(8) NOT NULL,
  delivery_count INT NOT NULL,
  status         TINYINT NOT NULL,
  create_time    DATETIME NOT NULL,

  update_time    DATETIME NOT NULL,
  create_by      VARCHAR(36) NOT NULL,
  update_by      VARCHAR(36) NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS promotion_coupons_use_records;
CREATE TABLE promotion_coupons_use_records (
  seq_no      VARCHAR(50) PRIMARY KEY ,
  coupons_id BIGINT ,
  user_id     VARCHAR(32) ,
  total_value VARCHAR(32)  ,
  use_value   VARCHAR(32)  ,
  freeze_status TINYINT ,
  use_time    DATETIME ,
  update_time  DATETIME
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




--reconciliation


-- ----------------------------
-- Table structure for `t_cash_transfer`
-- ----------------------------
DROP TABLE IF EXISTS `t_cash_transfer`;
CREATE TABLE `t_cash_transfer` (
  `ID` bigint(32) DEFAULT NULL,
  `TRANS_DATE` date NOT NULL COMMENT '业务日期',
  `DELETE_FLAG` tinyint(4) NOT NULL COMMENT '删除标记',
  `TRANSFER_TYPE` tinyint(4) DEFAULT NULL COMMENT '交易类型',
  `ORD_ID` varchar(128) DEFAULT NULL COMMENT '订单号',
  `ORD_DATE` date DEFAULT NULL COMMENT '订单日期',
  `MER_CUST_ID` varchar(128) DEFAULT NULL COMMENT '商户客户号',
  `INVEST_CUST_ID` varchar(128) DEFAULT NULL COMMENT '投资人客户号',
  `BORR_CUST_ID` varchar(128) DEFAULT NULL COMMENT '借款人客户号',
  `TRANS_AMT` double(19,0) DEFAULT NULL COMMENT '交易金额',
  `PNR_DATE` date DEFAULT NULL COMMENT '汇付交易日期',
  `PNR_SEQID` varchar(128) DEFAULT NULL COMMENT '汇付交易流水',
  `TRANS_STAT` varchar(4) DEFAULT NULL COMMENT '汇付取现交易状态',
  `FEE_OBJ_TYPE` tinyint(4) DEFAULT NULL COMMENT '手续费收取方',
  `FEE_AMT` double(19,0) DEFAULT NULL COMMENT '手续费金额',
  `SERV_FEE` double(19,0) DEFAULT NULL COMMENT '商户收取服务费金额',
  `SERV_FEE_ACCTID` varchar(128) DEFAULT NULL COMMENT '商户子账户号',
  `VERSION` tinyint(4) DEFAULT NULL,
  `CREATED` timestamp NULL DEFAULT '1971-01-01 00:00:00',
  `MODIFIED` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cash_transfer
-- ----------------------------

-- ----------------------------
-- Table structure for `t_loanpayment_transfer`
-- ----------------------------
DROP TABLE IF EXISTS `t_loanpayment_transfer`;
CREATE TABLE `t_loanpayment_transfer` (
  `ID` bigint(32) DEFAULT NULL,
  `TRANS_DATE` date NOT NULL COMMENT '业务日期',
  `DELETE_FLAG` tinyint(4) NOT NULL COMMENT '删除标记',
  `TRANSFER_TYPE` tinyint(4) NOT NULL COMMENT '交易类型',
  `ORD_ID` varchar(128) DEFAULT NULL COMMENT '订单号',
  `ORD_DATE` date DEFAULT NULL COMMENT '订单日期',
  `MER_CUST_ID` varchar(128) DEFAULT NULL COMMENT '商户客户号',
  `INVEST_CUST_ID` varchar(128) DEFAULT NULL,
  `BORR_CUST_ID` varchar(128) DEFAULT NULL COMMENT '借款人客户号',
  `TRANS_AMT` double(19,0) DEFAULT NULL COMMENT '交易金额',
  `PNR_DATE` date DEFAULT NULL COMMENT '汇付交易日期',
  `PNR_SEQID` varchar(256) DEFAULT NULL COMMENT '汇付交易流水',
  `TRANS_STAT` varchar(4) DEFAULT NULL COMMENT '汇付交易状态',
  `VERSION` tinyint(4) DEFAULT NULL,
  `CREATED` timestamp NULL DEFAULT '1971-01-01 00:00:00',
  `MODIFIED` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_loanpayment_transfer
-- ----------------------------

-- ----------------------------
-- Table structure for `t_transfer_div`
-- ----------------------------
DROP TABLE IF EXISTS `t_transfer_div`;
CREATE TABLE `t_transfer_div` (
  `ID` bigint(32) DEFAULT NULL,
  `TRANSFER_ID` bigint(32) DEFAULT NULL,
  `ACCT_ID` varchar(128) DEFAULT NULL COMMENT '分账账户号',
  `AMT` double(19,0) DEFAULT NULL COMMENT '分账金额',
  `VERSION` tinyint(4) DEFAULT NULL,
  `CREATED` timestamp NULL DEFAULT '1971-01-01 00:00:00',
  `MODIFIED` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `PNR_SEQID` varchar(128) DEFAULT NULL,
  `CUST_ID` varchar(128) DEFAULT NULL COMMENT '分账客户号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_transfer_div
-- ----------------------------




----trade



DROP TABLE IF EXISTS `recharge_l`;
create table `recharge_l`(
    id int unsigned NOT NULL  COMMENT '自增id',
    seq_no varchar(32) NOT NULL default '' COMMENT '交易流水号',
    uid varchar(32) NOT NULL default '' COMMENT '平台用户id',
    user_cust_id varchar(16) NOT NULL default '' COMMENT '汇付生成的用户id',
    amt decimal(14, 2) NOT NULL default 0 COMMENT '金额',

    arrive_amt decimal(14, 2) NOT NULL default 0 COMMENT '实际充值金额',
    recharge_type varchar(10) NOT NULL default '' COMMENT '充值类型;invest:投资;finance:融资',
    order_id varchar(20) NOT NULL default '' COMMENT '订单号',
    order_date varchar(8) NOT NULL default '' COMMENT '订单日期',
    gate_business_id varchar(6) NOT NULL default '' COMMENT '支付网关业务代号',

    open_bank_id varchar(8) NOT NULL default '' COMMENT '开户银行代号',
    dc_flag char(1) NOT NULL default '' COMMENT '借贷记标记',
    return_url varchar(128) NOT NULL default '' COMMENT '页面返回url',
    open_acct_id varchar(32) NOT NULL default '' COMMENT '银行卡号',
    id_card varchar(18) NOT NULL default '' COMMENT '身份证号',

    page_type char(1) NOT NULL default '' COMMENT '页面类型',
    fee_amt decimal(14,2) NOT NULL default 0 COMMENT '手续费金额',
    fee_cust_id varchar(16) NOT NULL default '' COMMENT '手续费扣款客户号',
    fee_acct_id varchar(9) NOT NULL default '' COMMENT '手续费扣款子账户号',
    create_time datetime NOT NULL default '0000-00-00 00:00:00' COMMENT '记录生成时间',

    update_time datetime NOT NULL default '0000-00-00 00:00:00' COMMENT '记录最近更改时间',
    start_time datetime NOT NULL default '0000-00-00 00:00:00' COMMENT '发起申请时间',
    finish_time datetime NOT NULL default '0000-00-00 00:00:00' COMMENT '处理结束时间',
    rework_yn char(1) NOT NULL default 'N' COMMENT '是否打回;Y:打回;N:不打回',
    status varchar(10) NOT NULL default 'init' COMMENT '状态;init:初始状态;process:处理中;sucess:处理成功;fail:失败',

    recharge_man_yn char(1) NOT NULL default 'N' COMMENT '是否人工补账',
    recharge_man_reason varchar(200) NOT NULL default '' COMMENT '人工补账原因'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `withdraw_l`;
create table `withdraw_l`(
    id int unsigned NOT NULL  COMMENT '自增id',
    seq_no varchar(32) NOT NULL default '' COMMENT '交易流水号',
    uid varchar(32) NOT NULL default '' COMMENT '用户id',
    user_cust_id varchar(16) NOT NULL default '' COMMENT '汇付生成的用户id',
    remark varchar(128) NOT NULL default '' COMMENT '备注',

    page_type char(1) NOT NULL default '' COMMENT '页面类型',
    response_code varchar(3) NOT NULL default '' COMMENT '应答返回码',
    response_desc varchar(100) NOT NULL default '' COMMENT '应答描述',
    trans_amt decimal(14, 2) NOT NULL default 0 COMMENT '交易金额',
    withdraw_type varchar(10) NOT NULL default '' COMMENT '提现类型;invest:投资;finance:融资',

    arrive_amt decimal(14, 2) NOT NULL default 0 COMMENT '实际到账金额',
    order_id varchar(20) NOT NULL default '' COMMENT '订单号',
    open_acct_id varchar(40) NOT NULL default '' COMMENT '取现银行的账户号(银行卡号)',
    open_bank_id varchar(8) NOT NULL default '' COMMENT '开户银行代号',
    fee_amt decimal(14,2) NOT NULL default 0 COMMENT '手续费收取金额',

    fee_cust_id varchar(16) NOT NULL default '' COMMENT '手续费扣款客户号',
    fee_acct_id varchar(9) NOT NULL default '' COMMENT '手续费扣款子账户号',
    service_fee decimal(14, 2) NOT NULL default 0 COMMENT '商户收取服务费金额',
    service_fee_acct_id varchar(9) NOT NULL default '' COMMENT '商户子账户号',
    return_url varchar(128) NOT NULL default '' COMMENT '页面返回url',

    bg_return_url varchar(128) NOT NULL default '' COMMENT '商户后台应答地址',
    create_time datetime NOT NULL default '0000-00-00 00:00:00' COMMENT '记录生成时间',
    update_time datetime NOT NULL default '0000-00-00 00:00:00' COMMENT '记录最近更改时间',
    status varchar(10) NOT NULL default 'init' COMMENT '处理状态;init:初始;process:处理中;sucess:处理成功;fail:失败'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `user_balance`;
create table `user_balance`(
    id int unsigned primary key NOT NULL auto_increment COMMENT '自增id',
    uid varchar(32) NOT NULL default '' COMMENT '用户id',
    operate_type varchar(10) NOT NULL default '' COMMENT '平台类型,invest:投资平台,finance:融资平台',
    usable_sa decimal(14,2) NOT NULL default 0 COMMENT '可用余额',
    withdraw_freeze_sa decimal(14,2) NOT NULL default 0 COMMENT '提现冻结金额-用于提现',

    invest_freeze_sa decimal(14,2) NOT NULL default 0 COMMENT '投资冻结金额-用于认购/转让; 认购：放款时减除; 转让：转让成功时减除',
    repay_freeze_sa decimal(14,2) NOT NULL default 0 COMMENT '还款冻结金额',
    capital_freeze_sa decimal(14,2) NOT NULL default 0 COMMENT '活期赎回冻结金额',
    current_capital decimal(14,2) NOT NULL default 0 COMMENT '活期本金',
    current_interest decimal(14,2) NOT NULL default 0 COMMENT '活期待收利息',

    current_total_capital decimal(14,2) NOT NULL default 0 COMMENT '活期累计投资本金',
    current_total_interest decimal(14,2) NOT NULL default 0 COMMENT '活期累计投资利息',
    fixend_capital decimal(14,2) NOT NULL default 0 COMMENT '定期赚待收本金',
    fixend_interest decimal(14,2) NOT NULL default 0 COMMENT '定期赚待收利息',
    fixend_total_capital decimal(14,2) NOT NULL default 0 COMMENT '定期赚累计投资本金',

    fixend_total_interest decimal(14,2) NOT NULL default 0 COMMENT '定期赚累计利息',
    fixperiod_capital decimal(14,2) NOT NULL default 0 COMMENT '定期计划待收本金',
    fixperiod_interest decimal(14,2) NOT NULL default 0 COMMENT '定期计划待收利息',
    fixperiod_total_capital decimal(14,2) NOT NULL default 0 COMMENT '定期计划累计投资本金',
    fixperiod_total_interest decimal(14,2) NOT NULL default 0 COMMENT '定期计划累计利息',

    recharge_total_amt decimal(14,2) NOT NULL default 0 COMMENT '累计充值总额',
    recharge_total_count int NOT NULL default 0 COMMENT '累计充值次数',
    withdraw_total_amt decimal(14,2) NOT NULL default 0 COMMENT '累计提现总额',
    withdraw_total_count int NOT NULL default 0 COMMENT '累计提现次数',
    update_time datetime NOT NULL default '0000-00-00 00:00:00' COMMENT '记录最近更改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;






--financial

DROP TABLE IF EXISTS `borrower_repayment_plan`;
CREATE TABLE `borrower_repayment_plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `repayment_date` date NOT NULL COMMENT '还款日期',
  `borrower` varchar(80) NOT NULL DEFAULT '' COMMENT '融资方',
  `project_code` varchar(32) NOT NULL DEFAULT '' COMMENT '项目标编号',
  `repayment_type` varchar(32) NOT NULL DEFAULT '' COMMENT '还款方式',
  `repayment_prn` decimal(14,2) NOT NULL DEFAULT '0.00' COMMENT '还款本金',
  `repayment_interest` decimal(14,2) NOT NULL DEFAULT '0.00' COMMENT '还款利息',
  `actual_repayment_prn` decimal(14,2) NOT NULL DEFAULT '0.00' COMMENT '实际还款本金',
  `actual_repayment_interest` decimal(14,2) NOT NULL DEFAULT '0.00' COMMENT '实际还款利息',
  `create_time` datetime NOT NULL COMMENT '记录生成时间',
  `update_time` datetime NOT NULL COMMENT '记录更新时间',
  PRIMARY KEY (`id`),
  KEY `borrower_repayment_plan_i1` (`repayment_date`,`project_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='融资方还款计划表';


DROP TABLE IF EXISTS `fix_prod_duedate_report`;
CREATE TABLE `fix_prod_duedate_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `due_date` date NOT NULL COMMENT '到期日期',
  `fix_plan_prn` decimal(14,2) NOT NULL DEFAULT '0.00' COMMENT '定期计划本金',
  `fix_plan_interest` decimal(14,2) NOT NULL DEFAULT '0.00' COMMENT '定期计划利息',
  `fix_project_prn` decimal(14,2) NOT NULL DEFAULT '0.00' COMMENT '定期项目本金',
  `fix_project_interest` decimal(14,2) NOT NULL DEFAULT '0.00' COMMENT '定期项目利息',
  `create_time` datetime NOT NULL COMMENT '记录生成时间',
  `update_time` datetime NOT NULL COMMENT '记录更新时间',
  PRIMARY KEY (`id`),
  KEY `fix_prod_duedate_report_i1` (`due_date`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定期产品到期时间表';


DROP TABLE IF EXISTS `platform_asset_report`;
CREATE TABLE `platform_asset_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `report_date` date NOT NULL,
  `current_asset_total` decimal(14,2) NOT NULL DEFAULT '0.00' COMMENT '流动性产品资产规模',
  `fix_plan_asset_total` decimal(14,2) NOT NULL DEFAULT '0.00' COMMENT '定期计划资产规模',
  `fix_project_asset_total` decimal(14,2) NOT NULL DEFAULT '0.00' COMMENT '定期项目资产规模',
  `usable_balance_total` decimal(14,2) NOT NULL DEFAULT '0.00' COMMENT '财主账户余额总数',
  `freeze_balance_total` decimal(14,2) NOT NULL DEFAULT '0.00' COMMENT '财主冻结金额总数',
  `create_time` datetime NOT NULL COMMENT '记录生成时间',
  `update_time` datetime NOT NULL COMMENT '记录更新时间',
  PRIMARY KEY (`id`),
  KEY `platform_asset_report_i1` (`report_date`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='平台资产管理规模报表';


DROP TABLE IF EXISTS `platform_expend_report`;
CREATE TABLE `platform_expend_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `expend_date` date NOT NULL COMMENT '支出日期',
  `expend_detail` varchar(32) NOT NULL DEFAULT '' COMMENT '支出明细',
  `expend_prn` decimal(14,2) NOT NULL DEFAULT '0.00' COMMENT '支出金额',
  `create_time` datetime NOT NULL COMMENT '记录生成时间',
  `update_time` datetime NOT NULL COMMENT '记录更新时间',
  PRIMARY KEY (`id`),
  KEY `platform_expend_report_i1` (`expend_date`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='平台线上支出报表';


DROP TABLE IF EXISTS `project_quota_report`;
CREATE TABLE `project_quota_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `report_date` date NOT NULL COMMENT '报表日期',
  `project_name` varchar(32) NOT NULL DEFAULT '' COMMENT '项目标名称',
  `project_code` varchar(32) NOT NULL DEFAULT '' COMMENT '项目标编号',
  `project_financing_amt` decimal(14,2) NOT NULL DEFAULT '0.00' COMMENT '项目计划融资总额度',
  `issued_amt` decimal(14,2) NOT NULL DEFAULT '0.00' COMMENT '已发标额度',
  `remain_issue_amt` decimal(14,2) NOT NULL DEFAULT '0.00' COMMENT '剩余可发标额度',
  `withdrew_amt` decimal(14,2) NOT NULL DEFAULT '0.00' COMMENT '融资方已提现金额',
  `repaid_prn` decimal(14,2) NOT NULL DEFAULT '0.00' COMMENT '已还本金',
  `unrepay_prn` decimal(14,2) NOT NULL DEFAULT '0.00' COMMENT '未还本金',
  `repaid_interest` decimal(14,2) NOT NULL DEFAULT '0.00' COMMENT '已还利息',
  `unrepay_interest` decimal(14,2) NOT NULL DEFAULT '0.00' COMMENT '未还利息',
  `create_time` date NOT NULL COMMENT '记录生成时间',
  `update_time` date NOT NULL COMMENT '记录生成时间',
  PRIMARY KEY (`id`),
  KEY `project_quota_report_i1` (`report_date`,`project_name`,`project_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='项目额度报表';


DROP TABLE IF EXISTS `user_rw_daily_report`;
CREATE TABLE `user_rw_daily_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `report_date` date NOT NULL COMMENT '报表日期',
  `recharge_amt` decimal(14,2) NOT NULL DEFAULT '0.00' COMMENT '充值金额',
  `withdraw_amt` decimal(14,2) NOT NULL DEFAULT '0.00' COMMENT '提现金额',
  `create_time` datetime NOT NULL COMMENT '记录生成时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录更新时间',
  PRIMARY KEY (`id`),
  KEY `user_rw_daily_report_i1` (`report_date`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='财主每日充值提现报表';

