
-- 复投次数表
CREATE TABLE `f_repeat` (
  `id` int(128) NOT NULL AUTO_INCREMENT COMMENT '自增',
  `user_id` int(128) DEFAULT NULL COMMENT '用户ID',
  `product_id` int(128) DEFAULT NULL COMMENT '产品ID',
  `times` int(128) DEFAULT NULL COMMENT '复投次数',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `count_back` int(11) DEFAULT '0' COMMENT '记录每轮返现次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='复投次数表';




