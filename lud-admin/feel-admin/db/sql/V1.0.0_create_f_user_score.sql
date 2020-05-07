
-- 积分表
CREATE TABLE `f_user_score` (
  `id` int(128) NOT NULL AUTO_INCREMENT  COMMENT '主键',
  `user_id` int(128)  DEFAULT NULL COMMENT '关联用户ID',
  `score` decimal(19,8) DEFAULT 0 COMMENT '可用投资余额',
  `freeze_score` decimal(19,8) unsigned zerofill DEFAULT 0 COMMENT '冻结投资余额',
  `earnings_score`  decimal(19,8) DEFAULT 0 COMMENT '收益余额',
  `freeze_earnings` decimal(19,8) unsigned zerofill DEFAULT 0 COMMENT '冻结收益余额',
  `score_type` tinyint(4) DEFAULT NULL COMMENT '积分类型1：佣金2：限购3：商城',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `score_user_id_type_index` (`user_id`,`score_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC COMMENT='用户积分表';
