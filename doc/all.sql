drop table if exists `physical_value`;
create table `physical_value`(
    `id` bigint not null comment 'id',
    `project_id` bigint not null comment '项目id',
    `val1` decimal(10,4) not null comment '应变值1',
    `val2` decimal(10,4) not null comment '应变值2',
    `val3` decimal(10,4) not null comment '应变值3',
    `val4` decimal(10,4) not null comment '应变值4',
    `val5` decimal(10,4) not null comment '应变值5',
    `val6` decimal(10,4) not null comment '应变值6',
    `tag` tinyint not null comment '断点位置（0表示没有，1表示第一个位置断了，以此类推）',
    `create_time` bigint(13) not null comment '创建时间-距离1970年1月1日的毫秒数',
    primary key (`id`)
)engine=innodb default charset=utf8mb4  comment '应变值';-- 创建索引
create index index_create_time ON physical_value(create_time);
INSERT INTO physical_value (val2, val1, val3, val4, val5, val6, id, create_time) VALUES (267.7500, 61.5043, 118.6680, 232.4906, 94.8208, 274.2150, 3, 1646123552647);
INSERT INTO physical_value (val2, val1, val3, val4, val5, val6, id, create_time) VALUES (267.7500, 61.5043, 118.6680, 232.4906, 94.8208, 274.2150, 4, 1646123552647);
INSERT INTO physical_value (val2, val1, val3, val4, val5, val6, id, create_time) VALUES (267.7500, 61.5043, 118.6680, 232.4906, 94.8208, 274.2150, 5, 1646123552647);
#上面的physical_value表将换成 fbg_value
drop table if exists `fbg_value`;
create table `fbg_value`(
     `id` bigint not null comment 'id',
     `project_id` bigint not null comment '项目id',
     `physical_value_info_id` bigint not null comment '属性编号',
     `value` decimal(10,4) not null comment '应变值',
     `tag` tinyint not null comment '断点位置（0表示没有，1表示第一个位置断了，以此类推）',
     `create_time` bigint(13) not null comment '创建时间-距离1970年1月1日的毫秒数',
     primary key (`id`)
)engine=innodb default charset=utf8mb4  comment '应变值';
drop table if exists `physical_value_info`;
create table `fbg_value_info`(
    `id` bigint not null comment 'id',
    `property_name` varchar(20) not null comment '属性名',
    primary key (`id`)
)engine=innodb default charset = utf8mb4 comment '光纤光栅传感阵列物理属性表';
insert into fbg_value_info (id, property_name) values (1, 'val1');
insert into fbg_value_info (id, property_name) values (2, 'val2');
insert into fbg_value_info (id, property_name) values (3, 'val3');
insert into fbg_value_info (id, property_name) values (4, 'val4');
insert into fbg_value_info (id, property_name) values (5, 'val5');
insert into fbg_value_info (id, property_name) values (6, 'val6');

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

drop table if exists `breakpoint_record`;
create table `breakpoint_record`(
    `id` bigint not null auto_increment comment 'ID',
    `tag` tinyint unique not null comment '断点位置（0表示没有，1表示第一个位置断了，以此类推）',
    `create_time` bigint(13) not null comment '创建时间-距离1970年1月1日的毫秒数',
    `state` tinyint default 0 comment  '是否维修完成（0表示没有，1表示已经完成维修）',
    `detail` varchar(40) default null comment '详情',
    primary key (`id`)
)engine = innodb default charset = utf8mb4 comment = '断点记录表（会不断生成同一个断点的位置记录）';


show variables like '%event_scheduler%';
#获取前一小时的时间
select date_sub(now(), interval 1 hour);

select min(create_time) create_time, tag from physical_value where (create_time > 3600 and tag != 0) group by tag;
                                   #{createTime,jdbcType=BIGINT}-3600000 and tag != 0;
#【bug】[42000][1055] Expression #1 of SELECT list is not in GROUP BY clause and contains nonaggregated column 'modbustcpDev.physical_value.id' which is not functionally dependent on columns in GROUP BY clause; this is incompatible with sql_mode=only_full_group_by
#SET @@GLOBAL.sql_mode="STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION";

show variables like '%max_connect_errors%';

flush hosts;

drop table if exists `breakpoint_record_finish`;
create table `breakpoint_record_finish`(
                                    `id` bigint not null auto_increment comment 'ID',
                                    `tag` tinyint not null comment '断点位置（0表示没有，1表示第一个位置断了，以此类推）',
                                    `create_time` bigint(13) not null comment '创建时间-距离1970年1月1日的毫秒数',
                                    `state` tinyint default 0 comment  '是否维修完成（0表示没有，1表示已经完成维修）',
                                    `detail` varchar(40) default null comment '详情',
                                    primary key (`id`)
)engine = innodb default charset = utf8mb4 comment = '断点记录表（会不断生成同一个断点的位置记录）';

drop table if exists `normal_range`;
create table `normal_range`(
                       `id` bigint not null auto_increment comment 'ID',
                       `val1_min_value` decimal(10,4) not null comment 'val1最小值',
                       `val2_min_value` decimal(10,4) not null comment 'val2最小值',
                       `val3_min_value` decimal(10,4) not null comment 'val3最小值',
                       `val4_min_value` decimal(10,4) not null comment 'val4最小值',
                       `val5_min_value` decimal(10,4) not null comment 'val5最小值',
                       `val6_min_value` decimal(10,4) not null comment 'val6最小值',
                       `val1_max_value` decimal(10,4) not null comment 'val1最大值',
                       `val2_max_value` decimal(10,4) not null comment 'val2最大值',
                       `val3_max_value` decimal(10,4) not null comment 'val3最大值',
                       `val4_max_value` decimal(10,4) not null comment 'val4最大值',
                       `val5_max_value` decimal(10,4) not null comment 'val5最大值',
                       `val6_max_value` decimal(10,4) not null comment 'val6最大值',
                       `create_time` bigint(13) not null comment '创建时间',
                       primary key (`id`)
)engine = innodb default charset = utf8mb4 comment = '物理值正常值范围表';

select * from normal_range where id=(select max(id) from normal_range);


