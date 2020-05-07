

-- app版本管理
CREATE TABLE `b_app_version` (
  `id` int(128) NOT NULL AUTO_INCREMENT,
  `url` varchar(128) DEFAULT NULL COMMENT 'URL地址',
  `version` varchar(255) DEFAULT NULL COMMENT '版本号',
  `type` tinyint(1) DEFAULT NULL COMMENT 'type: 1 ios/ 2apk',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态：0待审核，1生效，2下架',
  `remark` varchar(128) DEFAULT NULL COMMENT '更新说明',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='app版本管理';


