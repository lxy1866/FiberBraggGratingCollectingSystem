drop table if exists `physical_value`;
create table `physical_value`(
    `id` bigint not null comment 'id',
    `val1` decimal(10,4) not null comment '应变值1',
    `val2` decimal(10,4) not null comment '应变值2',
    `val3` decimal(10,4) not null comment '应变值3',
    `val4` decimal(10,4) not null comment '应变值4',
    `val5` decimal(10,4) not null comment '应变值5',
    `val6` decimal(10,4) not null comment '应变值6',
    `create_time` bigint(13) not null comment '创建时间-距离1970年1月1日的毫秒数',
    primary key (`id`)
)engine=innodb default charset=utf8mb4  comment '应变值';
INSERT INTO physical_value (val2, val1, val3, val4, val5, val6, id, create_time) VALUES (267.7500, 61.5043, 118.6680, 232.4906, 94.8208, 274.2150, 3, 1646123552647);
INSERT INTO physical_value (val2, val1, val3, val4, val5, val6, id, create_time) VALUES (267.7500, 61.5043, 118.6680, 232.4906, 94.8208, 274.2150, 4, 1646123552647);
INSERT INTO physical_value (val2, val1, val3, val4, val5, val6, id, create_time) VALUES (267.7500, 61.5043, 118.6680, 232.4906, 94.8208, 274.2150, 5, 1646123552647);

drop table if exists `user`;
create table `user`(
                       `id` bigint not null comment 'ID',
                       `login_name` varchar(50) not null comment '登陆名',
                       `name` varchar(50) comment '昵称',
                       `password` char(32) not null comment '密码',
                       primary key (`id`),
                       unique key `login_name_unique` (`login_name`)
)engine = innodb default charset = utf8mb4 comment = '用户';

insert into `user` (id, `login_name`, `name`, `password`) values (1, 'test','测试','test');