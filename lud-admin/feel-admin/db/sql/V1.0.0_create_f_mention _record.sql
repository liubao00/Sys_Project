
-- 提币记录表

CREATE TABLE `f_mention_record` (
 `id` int(128) NOT NULL AUTO_INCREMENT COMMENT '主键',
 `user_id` int(128)  NOT NULL COMMENT '用户ID',
 `score` decimal(11,6) NOT NULL COMMENT '提现的积分',
 `number` decimal(11,6) NOT NULL COMMENT '提币个数',
 `fee` decimal(11,6) DEFAULT NULL COMMENT '手续费',
 `hash` varchar(128) COMMENT '提币hash值',
 `account` varchar(128)  DEFAULT NULL COMMENT '提现地址',
 `type` tinyint(4) DEFAULT NULL COMMENT '类型: 1.本金提现  2.收益提现',
 `state` tinyint(4) DEFAULT NULL COMMENT '状态：1：申请中 2:审核通过,提币中 3提币完成 4审核不通过',
 `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
 `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
 PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='提币记录表';
