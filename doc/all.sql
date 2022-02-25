drop table if exists `physical_value`;
create table `physical_value`(
    `id` bigint not null comment 'id',
    `val1` decimal(10,4) not null comment '应变值1',
    `val2` decimal(10,4) not null comment '应变值2',
    `val3` decimal(10,4) not null comment '应变值3',
    `val4` decimal(10,4) not null comment '应变值4',
    `val5` decimal(10,4) not null comment '应变值5',
    `val6` decimal(10,4) not null comment '应变值6',
    `create_time` datetime(3) default '1970-01-01
00:00:00.000' not null comment '创建时间',
    primary key (`id`)
)engine=innodb default charset=utf8mb4  comment '应变值';
INSERT INTO physical_value (val2, val1, val3, val4, val5, val6, id) VALUES (267.7500, 61.5073, 118.6680, 232.4906, 94.8208, 274.2150, 1);
INSERT INTO physical_value (val2, val1, val3, val4, val5, val6, id) VALUES (267.7500, 61.5043, 118.6680, 232.4906, 94.8208, 274.2150, 2);
INSERT INTO physical_value (val2, val1, val3, val4, val5, val6, id, create_time) VALUES (267.7500, 61.5043, 118.6680, 232.4906, 94.8208, 274.2150, 3, '2017-10-27 12:15:42.664');
INSERT INTO physical_value (val2, val1, val3, val4, val5, val6, id, create_time) VALUES (267.7500, 61.5043, 118.6680, 232.4906, 94.8208, 274.2150, 4, '2022-2-23 12:15:42.664');
INSERT INTO physical_value (val2, val1, val3, val4, val5, val6, id, create_time) VALUES (267.7500, 61.5043, 118.6680, 232.4906, 94.8208, 274.2150, 5, '2017-2-23 12:15:42.664');

drop table if exists `user`;
create table `user`(
                       `id` bigint not null comment 'ID',
                       `login_name` varchar(50) not null comment '登陆名',
                       `name` varchar(50) comment '昵称',
                       `password` char(32) not null comment '密码',
                       primary key (`id`),
                       unique key `login_name_unique` (`login_name`)
)engine = innodb default charset = utf8mb4 comment = '用户';