
-- 节点配置参数
INSERT INTO `db_lud`.`sys_config` (`id`, `param_key`, `param_value`, `status`, `remark`) VALUES ('2', 'chain_url', 'http://47.56.119.58:10080/v1/createNewAddress', '1', '节点服务-usdt地址');
INSERT INTO `db_lud`.`sys_config` (`id`, `param_key`, `param_value`, `status`, `remark`) VALUES ('3', 'chain_url_eth_address', 'http://47.56.119.58:10080/v1/createNewEthAddress', '1', '节点服务-eth地址');
INSERT INTO `db_lud`.`sys_config` (`id`, `param_key`, `param_value`, `status`, `remark`) VALUES ('4', 'addressNum', '1', '1', '生成地址数量');


-- 层级关系初始化账号
INSERT INTO `db_lud`.`f_user` (`id`, `username`, `password`, `pay_password`, `status`, `role_id`, `invitation_code`, `id_card`, `real_name`, `idcard_front`, `idcard_reverse`, `gender`, `nickname`, `city`, `address_usdt`, `address_eth`, `type`, `last_login_ip`, `last_login_time`, `create_time`, `update_time`) VALUES ('1', '13142084341', '473287f8298dba7163a897908958f7c0eae733e25d2e027992ea2edc9bed2fa8', '473287f8298dba7163a897908958f7c0eae733e25d2e027992ea2edc9bed2fa8', '1', '0', '12345678', NULL, 'string', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', '0:0:0:0:0:0:0:1', '2019-08-26 21:47:46', '2019-08-26 21:47:46', '2019-08-26 21:47:46');
INSERT INTO `db_lud`.`f_user_relation_ship` (`id`, `user_id`, `parent_id`, `create_time`, `update_time`, `id_key`) VALUES ('1', '1', '1', '2019-08-26 21:47:46', '2019-08-26 21:47:46', '0');



-- 手续费率
INSERT INTO `db_lud`.`sys_config` (`param_key`, `param_value`, `status`, `remark`) VALUES ( 'charge_score', '2', '1', '提现手续费率');
INSERT INTO `db_lud`.`sys_config` (`param_key`, `param_value`, `status`, `remark`) VALUES ( 'charge_rate', '1', '1', '冲币汇率');
INSERT INTO `db_lud`.`sys_config` (`param_key`, `param_value`, `status`, `remark`) VALUES ( 'mention_rate', '0.02', '1', '提现手续费率');
