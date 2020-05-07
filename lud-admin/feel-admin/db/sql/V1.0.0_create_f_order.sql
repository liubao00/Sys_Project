
-- 拍单订单表

CREATE TABLE `f_order` (
  `id` int(128) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `serial_no` varchar(128) DEFAULT NULL COMMENT '订单编号',
  `user_id` int(128) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(128) DEFAULT NULL COMMENT '用户名',
  `product_id` int(128) DEFAULT NULL COMMENT '商品id',
  `product_name` varchar(128) DEFAULT NULL COMMENT '商品名',
  `picture` varchar(4000) DEFAULT NULL COMMENT '图片',
  `unit_price` decimal(19,8) DEFAULT NULL COMMENT '单价',
  `times` int(11) DEFAULT NULL COMMENT '复投次数',
  `total_price` decimal(19,8) DEFAULT NULL COMMENT '单品总价',
  `score_back` decimal(19,8) DEFAULT NULL COMMENT '返还佣金',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '支付时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '取消时间',
  `status` tinyint(4) DEFAULT '0' COMMENT '商品状态 0待支付，1支付成功 2订单失效 3',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `id` (`id`) USING BTREE,
  KEY `product_id` (`product_id`) USING BTREE,
  KEY `product_name` (`product_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='限购订单详情表';



