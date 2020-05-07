-- ----------------------------
-- Table structure for f_user
-- ----------------------------
DROP TABLE IF EXISTS `f_user`;
CREATE TABLE `f_user` (
  `id` int(128) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(128) NOT NULL COMMENT '用户名',
  `password` varchar(128) DEFAULT NULL COMMENT '密码',
  `pay_password` varchar(128) DEFAULT NULL COMMENT '支付密码',
  `status` int(11) NOT NULL COMMENT '用户状态  1正常  2冻结 3禁用',
  `role_id` int(128) DEFAULT NULL COMMENT '角色id',
  `invitation_code` varchar(32) NOT NULL COMMENT '邀请码',
  `id_card` varchar(128)  DEFAULT NULL COMMENT '身份证',
  `real_name` varchar(128)  DEFAULT NULL COMMENT '真实姓名',
  `idcard_front` varchar(128)  DEFAULT NULL COMMENT '身份证正面',
  `idcard_reverse` varchar(128)  DEFAULT NULL COMMENT '身份证反面',
  `gender` int(11) DEFAULT NULL COMMENT '性别 1男 2女',
  `nickname` varchar(128)  DEFAULT NULL COMMENT '昵称',
  `city` varchar(128)  DEFAULT NULL COMMENT '城市',
  `address_usdt` varchar(128)  DEFAULT NULL COMMENT 'usdt钱包地址',
  `address_eth` varchar(128)  DEFAULT NULL COMMENT 'eth钱包地址',
  `type` int(11) DEFAULT 0 COMMENT '审核状态 0未审核 1审核成功 2审核拒绝',
  `last_login_ip` varchar(128) DEFAULT NULL COMMENT '上一次登录的ip地址',
  `last_login_time` timestamp NULL DEFAULT NULL COMMENT '上一次登录时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `username` (`username`),
  KEY `status` (`status`),
  KEY `role_id` (`role_id`),
  KEY `invitation_code` (`invitation_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='前端用戶表';


-- 用户Token表
CREATE TABLE `f_token` (
  `user_id` int(128) NOT NULL COMMENT '主键',
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime COMMENT '过期时间',
  `update_time` datetime COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户Token';

CREATE TABLE `f_user_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL COMMENT '手机号',
  `code` varchar(100) NOT NULL COMMENT '验证码',
  `type` tinyint(4) DEFAULT '1' COMMENT '类型   1：注册   2：修改',
  `create_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='验证码';

--
-- DROP TABLE IF EXISTS `f_role`;
-- CREATE TABLE `f_role`  (
--   `id` varchar(128) CHARACTER SET utf8mb4  NOT NULL COMMENT 'id',
--   `name` varchar(128) CHARACTER SET utf8mb4  NOT NULL COMMENT '角色名',
--   `describ` varchar(128) CHARACTER SET utf8mb4  NOT NULL COMMENT '描述',
--   `status` decimal(4, 0) NOT NULL COMMENT '用户状态',
--   `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
--   `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
--   PRIMARY KEY (`id`) USING BTREE,
--     index(`name`),
--     index(`describ`),
--     index(`status`)
-- ) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '前端用户角色表' ROW_FORMAT = Dynamic;
--
-- INSERT INTO f_role (id, name, describ, status, create_time, update_time) VALUES ('1', 'member', '会员', 1, '2019-07-03 06:43:06', '2019-07-03 06:43:10');
-- INSERT INTO f_role (id, name, describ, status, create_time, update_time) VALUES ('2', 'merchant', '商户', 1, '2019-07-03 06:43:40', '2019-07-03 06:43:44');

ALTER TABLE f_user add automatic_order int(11) DEFAULT '1' COMMENT '是否复投  1:复投 0:不复投';

