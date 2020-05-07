
-- 广告表
CREATE TABLE `f_ad` (
  `id` int(128) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(63) NOT NULL DEFAULT '' COMMENT '广告标题',
  `link` varchar(255) DEFAULT '' COMMENT '所广告的商品页面或者活动页面链接地址',
  `url` varchar(255) DEFAULT '' COMMENT '广告宣传图片',
  `position` tinyint(3) DEFAULT '1' COMMENT '广告位置：1是首页轮播 2首页广告位 3商户广告 4公告',
  `content` varchar(4000) DEFAULT '' COMMENT '活动内容',
  `start_time` datetime DEFAULT NULL COMMENT '广告开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '广告结束时间',
  `type` tinyint(1) DEFAULT '1' COMMENT '类型:1banaer 2xxx',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态:1启用 2停用 3逻辑删除',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间\n',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='广告表';

