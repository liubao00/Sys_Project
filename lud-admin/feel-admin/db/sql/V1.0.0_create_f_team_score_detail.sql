CREATE TABLE `f_user_team_score_detail` (
  `id` int(128) NOT NULL AUTO_INCREMENT COMMENT '自增',
  `user_id` int(128) DEFAULT NULL COMMENT '用户ID',
  `product_id` int(128) DEFAULT NULL COMMENT '产品ID',
  `section` tinyint(4) DEFAULT '0' COMMENT '用户区间 0未分配 1A区间  2B区间',
  `grand_total` decimal(65,8) DEFAULT NULL COMMENT '累计奖金',
  `data_time` varchar(255) DEFAULT NULL COMMENT '日期：yyyy-MM-dd',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态：1冻结，2返现完成，3返现失败',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='AB区奖金统每日统计表';