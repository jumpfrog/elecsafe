INSERT INTO `sys_buttondef` VALUES ('1301', '新增', '51020000', 'sys/account_addAccount.action', 'BTN_ADD', null);
INSERT INTO `sys_buttondef` VALUES ('1302', '修改', '51020000', 'sys/account_editAccount.action', 'BTN_EDIT', null);
INSERT INTO `sys_buttondef` VALUES ('1303', '删除', '51020000', 'sys/account_delAccount.action', 'BTN_DEL', null);
INSERT INTO `sys_buttondef` VALUES ('1304', '初始化密码', '51020000', 'sys/account_initPwd.action', 'BTN_INITPWD', null);
INSERT INTO `sys_buttondef` VALUES ('1305', '新增', '51010000', 'sys/role_addRole.action', 'BTN_ADD', null);
INSERT INTO `sys_buttondef` VALUES ('1306', '修改', '51010000', 'sys/role_editRole.action', 'BTN_EDIT', null);
INSERT INTO `sys_buttondef` VALUES ('1307', '删除', '51010000', 'sys/role_delRole.action', 'BTN_DEL', null);
INSERT INTO `sys_buttondef` VALUES ('1308', '分配权限', '51010000', 'sys/role_savePermission.action', 'BTN_OPERATE', null);
INSERT INTO `sys_buttondef` VALUES ('1309', '新增', '52010000', 'record/enterprise_addEnt.action', 'BTN_ADD', null);
INSERT INTO `sys_buttondef` VALUES ('1310', '修改', '52010000', 'record/enterprise_modifyEnt.action', 'BTN_EDIT', null);
INSERT INTO `sys_buttondef` VALUES ('1311', '删除', '52010000', 'record/enterprise_delEnt', 'BTN_DEL', null);
INSERT INTO `sys_buttondef` VALUES ('1312', '新增', '52020000', 'detector/detectorAddOrEdit.action', 'BTN_ADD', null);
INSERT INTO `sys_buttondef` VALUES ('1313', '修改', '52020000', 'detector/detectorAddOrEdit.action', 'BTN_EDIT', null);
INSERT INTO `sys_buttondef` VALUES ('1314', '删除', '52020000', 'record/detector_delDetector.action', 'BTN_DEL', null);
INSERT INTO `sys_buttondef` VALUES ('1315', '处理', '55000000', 'event/eventLog_saveEventDeal.action', null, null);




INSERT INTO `sys_moduledef` VALUES ('51000000', '3', '系统管理', '0', '-1', null, '51000000', '6', 'desktop', null, null);
INSERT INTO `sys_moduledef` VALUES ('51010000', '3', '角色管理', '0', '-1', 'sys/role.action', '51000000', '1', null, null, null);
INSERT INTO `sys_moduledef` VALUES ('51020000', '3', '用户管理', '0', '-1', 'sys/account.action', '51000000', '1', null, null, null);
INSERT INTO `sys_moduledef` VALUES ('51030000', '3', '登录日志', '0', '-1', 'sys/webLog.action', '51000000', '2', null, null, null);
INSERT INTO `sys_moduledef` VALUES ('51040000', '3', '操作日志', '0', '-1', 'sys/hostLog.action', '51000000', '3', null, null, null);
INSERT INTO `sys_moduledef` VALUES ('52000000', '3', '档案管理', '0', '-1', null, '52000000', '5', ' file', null, null);
INSERT INTO `sys_moduledef` VALUES ('52010000', '3', '企业信息', '0', '-1', 'record/enterprise.action', '52000000', '1', null, null, null);
INSERT INTO `sys_moduledef` VALUES ('52020000', '3', '漏电探测点', '0', '-1', 'detector/detectorList.action', '52000000', '2', null, null, null);
INSERT INTO `sys_moduledef` VALUES ('53000000', '3', '实时数据', '0', '-1', 'real/enterpriseList.action', '53000000', '1', 'database', null, null);
INSERT INTO `sys_moduledef` VALUES ('54000000', '3', '历史数据', '0', '-1', 'history/historyList.action', '54000000', '2', 'history', null, null);
INSERT INTO `sys_moduledef` VALUES ('55000000', '3', '事件日志', '0', '-1', 'event/eventLog.action', '55000000', '3', 'bell-o', null, null);
INSERT INTO `sys_moduledef` VALUES ('56000000', '3', '监测报表', '0', '-1', 'stat/monitorReport.action', '56000000', '4', 'line-chart', null, null);
