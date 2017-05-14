DROP TABLE IF EXISTS `t_user`;
create table t_user(
	user_id int not null AUTO_INCREMENT COMMENT '用户id',
	organization_id int not null  COMMENT '所属组织',
	login_name VARCHAR(64) not null comment '登录帐号',
	password VARCHAR(64) not null comment '登录密码',
	name VARCHAR(64) not null comment '昵称',
	user_uuid VARCHAR(64) not null comment '用户uuid',
	login_type char(2) not null comment '登录类型',
	available char(2) not null comment '是否可用',
	salt varchar(10) not null comment '随机，长度为5',
	ip varchar(20) not null comment 'ip地址',
	creattime datetime not null comment '创建时间',
  PRIMARY KEY (`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8,
comment='用户表';

DROP TABLE IF EXISTS `t_user_info`;
create table t_user_info(
	user_id int not null COMMENT '用户id',
	user_name varchar(64) COMMENT '用户名称',
	native_place varchar(64) COMMENT '籍贯', 
	interest varchar(300) COMMENT '兴趣爱好',
	constellation varchar(64) COMMENT '星座',
	education varchar(64) COMMENT '学历', 
	nationa_lity varchar(64) COMMENT '民族', 
	sex char(2) COMMENT '性别', 
	mobile varchar(20) COMMENT '手机号', 
	email varchar(64) COMMENT '电子邮箱',
	birthday date  COMMENT '出生日期',
	university varchar(64) COMMENT '毕业院校',
	image_id int COMMENT '照片',
	major varchar(64) COMMENT '专业',
	academy varchar(64) COMMENT '学院',
	career varchar(64) COMMENT '职业',
	company varchar(64) COMMENT '公司',
  address varchar(200) COMMENT '地址',
	PRIMARY KEY (`user_id`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8,
comment='用户信息表';

DROP TABLE IF EXISTS `t_image`;
create table t_image(
	image_id int not null AUTO_INCREMENT COMMENT '图片id',
	image_name varchar(64) COMMENT '图片名称',
	native_place varchar(64) COMMENT '籍贯', 
	url varchar(300) COMMENT '兴趣爱好',
	image_type char(2) COMMENT '图片类型', 
	PRIMARY KEY (`image_id`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8,
comment='图片表';

DROP TABLE IF EXISTS `t_role`;
create table t_role(
	role_id int not null AUTO_INCREMENT COMMENT '角色ID',
	parent_role_id int COMMENT '父级角色ID',
	role_name varchar(64) not null COMMENT '角色名称', 
	create_time datetime COMMENT '创建时间',
	available char(2) COMMENT '是否可用', 
	description varchar(300) COMMENT '角色描述',
	PRIMARY KEY (`role_id`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8,
comment='角色表';

DROP TABLE IF EXISTS `t_privilege`;
create table t_privilege(
	privilege_id int not null AUTO_INCREMENT COMMENT '权限ID',
	parent_privilege_id int COMMENT '父权限',
	privilege_name varchar(64) not null COMMENT '权限名称', 
	create_time datetime COMMENT '创建时间',
	available char(2) COMMENT '是否可用', 
	description varchar(300) COMMENT '角色描述',
	PRIMARY KEY (`privilege_id`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8,
comment='权限表';

DROP TABLE IF EXISTS `t_group`;
create table t_group(
	group_id int not null AUTO_INCREMENT COMMENT '组ID',
	group_name varchar(64) not null COMMENT '组名称', 
	parent_group_id int COMMENT '父组', 
	create_time datetime COMMENT '创建时间',
	available char(2) COMMENT '是否可用', 
	description varchar(300) COMMENT '角色描述',
	PRIMARY KEY (`group_id`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8,
comment='租表';

DROP TABLE IF EXISTS `t_role_privilege`;
create table t_role_privilege
(
	role_privilege_id int not null AUTO_INCREMENT COMMENT '记录标识',
	role_id int not null COMMENT '角色', 
	privilege_id int not null COMMENT '权限', 
	privilege_type  char(2) COMMENT '权限类型', 
	PRIMARY KEY (`role_privilege_id`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8,
comment='角色权限表';

DROP TABLE IF EXISTS `t_group_privilege`;
create table t_group_privilege
(
	group_privilege_id int not null AUTO_INCREMENT COMMENT '记录标识',
	group_id int not null COMMENT '组', 
	privilege_id int not null COMMENT '权限', 
	privilege_type  char(2) COMMENT '权限类型', 
	PRIMARY KEY (`group_privilege_id`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8,
comment='组权限表';

DROP TABLE IF EXISTS `t_group_role`;
create table t_group_role
(
	group_role_id int not null AUTO_INCREMENT COMMENT '记录标识',
	group_id int not null COMMENT '角色', 
	role_id int not null COMMENT '权限',
	PRIMARY KEY (`group_role_id`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8,
comment='组角色表';

DROP TABLE IF EXISTS `t_user_privilege`;
create table t_user_privilege
(
	user_privilege_id int not null AUTO_INCREMENT COMMENT '记录标识',
	user_id int not null COMMENT '用户', 
	privilege_id int not null COMMENT '权限',
	privileges_type char(2) COMMENT '权限类型', 
	PRIMARY KEY (`user_privilege_id`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8,
comment='用户权限表';

DROP TABLE IF EXISTS `t_user_role`;
create table t_user_role
(
	user_role_id int not null AUTO_INCREMENT COMMENT '记录标识',
	user_id int not null COMMENT '用户', 
	role_id int not null COMMENT '角色',
	PRIMARY KEY (`user_role_id`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8,
comment='用户角色表';


DROP TABLE IF EXISTS `t_user_group`;
create table t_user_group
(
	user_group_id int not null AUTO_INCREMENT COMMENT '记录标识',
	user_id int not null COMMENT '用户', 
	group_id int not null COMMENT '组',
	PRIMARY KEY (`user_group_id`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8,
comment='用户组表';

DROP TABLE IF EXISTS `t_organization`;
create table t_organization
(
	organization_id int not null AUTO_INCREMENT COMMENT '组织id',
	parent_organization_id int COMMENT '父组织id', 
	organization_name varchar(64) not null COMMENT '组织名称',
	create_time datetime not null COMMENT '创建时间',
	description varchar(64) COMMENT '组织描述', 
	PRIMARY KEY (`organization_id`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8,
comment='组织表';

DROP TABLE IF EXISTS `t_log_operation`;
create table t_log_operation
(
	operation_id int not null AUTO_INCREMENT COMMENT '组织id',
	log_id int not null COMMENT '日志id', 
	operation_type char(2) not null COMMENT '操作类型',
	primary_key_value int not null COMMENT '操作主键',
	content varchar(200) not null COMMENT '操作内容', 
	user_id int not null COMMENT '用户id', 
	operation_time datetime not null COMMENT '操作时间',
	PRIMARY KEY (`operation_id`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8,
comment='操作日志表';

DROP TABLE IF EXISTS `log_setting`;
create table log_setting
(
	log_id int not null AUTO_INCREMENT COMMENT '日志id',
	table_name int not null COMMENT '表名', 
	bussiness_name varchar(64) not null COMMENT '业务名称',
	primary_key int not null COMMENT '操作主键',
	url_template varchar(200) not null COMMENT '操作内容', 
	user_id int not null COMMENT '用户id', 
	operation_time datetime not null COMMENT '操作时间',
	delete_script_template varchar(200) not null COMMENT '', 
	script_template varchar(200) not null COMMENT '', 
	PRIMARY KEY (`log_id`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8,
comment='日志设置表';


DROP TABLE IF EXISTS `log_setting_detail`;
create table log_setting_detail
(
	detail_id int not null AUTO_INCREMENT COMMENT '详细日志id',
	log_id int not null COMMENT '组织id',
	column_name varchar(64) not null COMMENT '字段名称', 
	column_text varchar(64) not null COMMENT '字段内容',
	column_data_type char(2) not null COMMENT '字段类型',
	user_id int not null COMMENT '用户id', 
	operation_time datetime not null COMMENT '操作时间',
	PRIMARY KEY (`detail_id`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8,
comment='日志设置详细表';

DROP TABLE IF EXISTS `t_parameter`;
create table t_parameter
(
	parameter_id int not null AUTO_INCREMENT COMMENT '参数id',
	parameter_code int not null COMMENT '参数代码',
	parameter_value varchar(64) not null COMMENT '参数值', 
	parameter_name varchar(64) not null COMMENT '参数名称',
	parameter_available char(2) not null COMMENT '可用标志',
	description varchar(200) not null COMMENT '参数描述', 
	PRIMARY KEY (`parameter_id`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8,
comment='参数表';


DROP TABLE IF EXISTS `t_login_out_log`;
create table t_login_out_log
(
	log_id int not null AUTO_INCREMENT COMMENT '日志id',
	login_address varchar(64) not null COMMENT '登录/登出地址',
	ip_address varchar(64) not null COMMENT '登录/登出IP', 
	user_name varchar(64) not null COMMENT '用户名称',
	user_id int not null COMMENT '用户ID',
	login_time datetime not null COMMENT '登录/登出时间', 
	login_type char(2) not null COMMENT '类型',
	mac_id varchar(64) not null COMMENT 'mac地址',
	PRIMARY KEY (`log_id`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8,
comment='登录登场日志表';

DROP TABLE IF EXISTS `t_menu`;
create table t_menu
(
	menu_id int not null AUTO_INCREMENT COMMENT '菜单id',
	menu_code varchar(64) not null COMMENT '菜单编码',
	url varchar(200) not null COMMENT '菜单路径', 
	menu_name varchar(64) not null COMMENT '菜单名称',
	image_url varchar(200) not null COMMENT '菜单路径',
	short_cut varchar(64) COMMENT '快捷键', 
	menu_order int not null COMMENT '菜单显示顺序',
	grade varchar(64) not null COMMENT '菜单级限',
	delete_mark char(2) COMMENT '删除标志',
	menu_type char(2) not null COMMENT '菜单类型',
	allow_edit char(2) not null COMMENT '允许编辑',
  super_menu int COMMENT '父菜单',
	PRIMARY KEY (`menu_id`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8,
comment='菜单表';

DROP TABLE IF EXISTS `t_menu_privilege`;
create table t_menu_privilege
(
	menu_privilege_id int not null AUTO_INCREMENT COMMENT '菜单权限id',
	privilege_id int not null COMMENT '父菜单',
	privileges_type char(2) not null COMMENT '菜单类型',
	menu_id int not null COMMENT '菜单id',
	PRIMARY KEY (`menu_privilege_id`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8,
comment='权限菜单表';

DROP TABLE IF EXISTS `t_category`;
create table t_category
(
	category_id int not null AUTO_INCREMENT COMMENT '类别id',
	category_name int not null COMMENT '类别名称',
	parent_id char(2) not null COMMENT '父类别',
	available char(2) not null COMMENT '是否可用',
	PRIMARY KEY (`category_id`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8,
comment='权限菜单表';
DROP TABLE IF EXISTS `t_category`;

create table t_quartz
(
	job_id int not null AUTO_INCREMENT COMMENT 'jobid',
	create_time int not null COMMENT '类别名称',
	update_time char(2) not null COMMENT '父类别',
	job_name char(2) not null COMMENT '任务名称',
	job_group char(2) not null COMMENT '任务分组',
	job_status char(2) not null COMMENT '任务状态,是否启动任务',
	cron_expression char(2) not null COMMENT 'cron表达式,什么时间执行',
	description char(2) not null COMMENT '描述',
	bean_class char(2) not null COMMENT '任务执行时调用哪个类的方法 包名+类名',
	is_concurrent char(2) not null COMMENT '任务是否有状态',
	spring_id char(2) not null COMMENT 'spring bean',
	method_name char(2) not null COMMENT '任务调用的方法名',
	PRIMARY KEY (`job_id`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8,
comment='任务调度表';