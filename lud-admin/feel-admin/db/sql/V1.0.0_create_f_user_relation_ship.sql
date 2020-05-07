

CREATE TABLE `f_user_relation_ship` (
  `id` int(128) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(128) DEFAULT NULL COMMENT '用户id',
  `section` tinyint(4) DEFAULT '0' COMMENT '用户区间 0未分配 1A区间  2B区间',
  `parent_id` int(128) NOT NULL COMMENT '父级id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `id_key` varchar(4000) CHARACTER SET latin1 COLLATE latin1_danish_ci NOT NULL COMMENT '用户层级路径',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `parent_id` (`parent_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='前端用戶层级关系表';
