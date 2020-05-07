
-- 冲币记录表

CREATE TABLE `f_charge_record` (
    `id` int(128) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id` int(128)  NOT NULL COMMENT '用户ID',
    `number` decimal(11,6) NOT NULL COMMENT '冲币个数',
    `score` decimal(11,6) NOT NULL COMMENT '积分数',
    `hash` varchar(128) COMMENT '冲币hash值',
    `account` varchar(128)  DEFAULT NULL COMMENT '冲币账号',
    `pic` varchar(521) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '冲币凭证',
    `state` tinyint(4) DEFAULT NULL COMMENT '状态：1申请中 2审核通过完成 3审核不通过',
    `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='提币记录表';
