
-- 积分流水表
CREATE TABLE `f_user_score_detail` (
  `id` int(128) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(128) DEFAULT NULL COMMENT '用户ID',
  `order_id` int(128) DEFAULT NULL COMMENT '订单ID',
  `score` decimal(19,8) DEFAULT NULL COMMENT '用户当前可用积分',
  `freeze_score` decimal(19,8) DEFAULT NULL COMMENT '用户当前冻结积分',
  `operate_type` tinyint(4) DEFAULT NULL COMMENT '操作类型；1：增加 2：减少',
  `operate_score` decimal(19,8) DEFAULT NULL COMMENT '本次操作积分',
  `detail_type` int(4) DEFAULT NULL COMMENT '操作积分的业务类型',
  `user_score_id` int(128) DEFAULT NULL COMMENT '关联用户积分表',
  `score_type` tinyint(4) DEFAULT NULL COMMENT '积分类型 1可用余额 2收益积分',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态：1.冻结中，2.冻结失败返现 3.冻结失败扣除4.已完成',
  `remark` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '备注，业务单据号，奖励类别等',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `back_time` timestamp NULL DEFAULT NULL COMMENT '返现时间',
  `product_id` int(128) DEFAULT NULL COMMENT '商品ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC COMMENT='用户积分表流水详情';

