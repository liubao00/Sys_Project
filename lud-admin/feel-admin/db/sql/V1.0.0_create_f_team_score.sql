
-- AB区奖金统计表
CREATE TABLE `f_user_team_score` (
  `id` int(128) NOT NULL AUTO_INCREMENT COMMENT '自增',
  `user_id` int(128) DEFAULT NULL COMMENT '用户ID',
  `section` tinyint(4) DEFAULT '0' COMMENT '用户区间 0未分配 1A区间  2B区间',
  `grand_total` decimal(65,8) DEFAULT NULL COMMENT '累计奖金',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='AB区奖金统计表';




