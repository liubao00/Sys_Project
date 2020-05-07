

CREATE TABLE `b_address_eth` (
  `id` int(128) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `address` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址',
  `value` int(11) DEFAULT NULL COMMENT '星空来客人',
  `status` tinyint(1) DEFAULT NULL COMMENT '0:未使用 1已使用',
  `remark`  varchar(128)  DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='eth地址池';

