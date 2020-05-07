

-- 合约矿池
INSERT INTO `db_lud`.`f_user` (`id`, `username`, `status`, `role_id`, `real_name`,`invitation_code`) VALUES ('11', '11111111111', '1', '0', '合约互助池',"111111");
INSERT INTO `db_lud`.`f_user` (`id`, `username`, `status`, `role_id`, `real_name`,`invitation_code`) VALUES ('22', '22222222222', '1', '0', '预留奖金池',"222222");
INSERT INTO `db_lud`.`f_user` (`id`, `username`, `status`, `role_id`, `real_name`,`invitation_code`) VALUES ('33', '33333333333', '1', '0', '共识池',"333333");

INSERT INTO `db_lud`.`f_user_score` (`user_id`) VALUES ('11');
INSERT INTO `db_lud`.`f_user_score` (`user_id`) VALUES ('22');
INSERT INTO `db_lud`.`f_user_score` (`user_id`) VALUES ('33');
