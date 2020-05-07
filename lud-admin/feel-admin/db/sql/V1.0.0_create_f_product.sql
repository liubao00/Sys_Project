
-- 排单商品表

CREATE TABLE `f_product` (
  `id` int(128) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '排单名称',
  `pic` varchar(521) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '排单图片',
  `score` decimal(19,8) DEFAULT NULL COMMENT '排单金额',
  `status` tinyint(4) DEFAULT '1' COMMENT '排单商品状态 1正常 2已售完 0逻辑删除',
  `remark` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '排单商品描述',
  `specification_desc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '规格描述',
  `specification` tinyint(10) DEFAULT NULL COMMENT '规格',
  `single_back` decimal(19,8) DEFAULT NULL COMMENT '静态奖励百分比',
  `double_back` decimal(19,8) DEFAULT NULL COMMENT '累计奖励百分比',
  `max_back` decimal(19,8) DEFAULT NULL COMMENT '上限奖励百分比',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='排单商品表';

