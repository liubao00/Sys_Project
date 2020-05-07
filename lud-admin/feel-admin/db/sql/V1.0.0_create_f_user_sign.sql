-- 用户签到表
CREATE TABLE `f_user_sign` (
  `id` int(128) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(128) NOT NULL COMMENT '用户id',
  `month` varchar(66) NOT NULL COMMENT '签到月份 2019-08',
  `results` varchar(128) DEFAULT '[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]' COMMENT '每天签到结果 数组[1,0,1] 1表示签到 0表示未签到',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户签到表';
