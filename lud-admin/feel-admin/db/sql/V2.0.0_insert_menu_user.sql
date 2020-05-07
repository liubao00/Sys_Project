-- 菜单SQL 前端用戶层级关系表
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES ('1', '前端用戶层级关系表', 'modules/app/fuserrelationship.html', NULL, '1', 'fa fa-file-code-o', '6');

-- 按钮父菜单ID
set @parentId = @@identity;

-- 菜单对应按钮SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '查看', null, 'app:fuserrelationship:list,app:fuserrelationship:info', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '新增', null, 'app:fuserrelationship:save', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '修改', null, 'app:fuserrelationship:update', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '删除', null, 'app:fuserrelationship:delete', '2', null, '6';



-- 菜单SQL 用户积分表
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES ('1', '用户积分表', 'modules/app/fuserscore.html', NULL, '1', 'fa fa-file-code-o', '6');

-- 按钮父菜单ID
set @parentId = @@identity;

-- 菜单对应按钮SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '查看', null, 'app:fuserscore:list,app:fuserscore:info', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '新增', null, 'app:fuserscore:save', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '修改', null, 'app:fuserscore:update', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '删除', null, 'app:fuserscore:delete', '2', null, '6';



-- 菜单SQL 用户积分表流水详情
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES ('1', '用户积分表流水详情', 'modules/app/fuserscoredetail.html', NULL, '1', 'fa fa-file-code-o', '6');

-- 按钮父菜单ID
set @parentId = @@identity;

-- 菜单对应按钮SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '查看', null, 'app:fuserscoredetail:list,app:fuserscoredetail:info', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '新增', null, 'app:fuserscoredetail:save', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '修改', null, 'app:fuserscoredetail:update', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '删除', null, 'app:fuserscoredetail:delete', '2', null, '6';
