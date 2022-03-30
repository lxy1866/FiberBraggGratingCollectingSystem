drop table if exists `fbg_value`;
create table `fbg_value`(
     `id` bigint not null comment 'id',
     `physical_value_info_id` bigint not null comment '属性编号',
     `value` decimal(10,4) not null comment '应变值',
     `tag` tinyint not null comment '断点位置（0表示没有，1表示第一个位置断了，以此类推）',
     `create_time` bigint(13) not null comment '创建时间-距离1970年1月1日的毫秒数',
     primary key (`id`)
)engine=innodb default charset=utf8mb4  comment '应变值';
alter table fbg_value modify id int auto_increment;
insert into fbg_value (id, physical_value_info_id, value, tag, create_time) values (1, 1, 11, 0, 1647907862069);
insert into fbg_value (id, physical_value_info_id, value, tag, create_time) values (2, 1, 11, 0, 1647907862069);
insert into fbg_value (id, physical_value_info_id, value, tag, create_time) values (3, 2, 22, 0, 1647907862069);
insert into fbg_value (id, physical_value_info_id, value, tag, create_time) values (4, 2, 22, 0, 1647907862069);

insert into fbg_value (id, physical_value_info_id, value, tag, create_time) values (5, 1, 11, 0, 1647907933365);
insert into fbg_value (id, physical_value_info_id, value, tag, create_time) values (6, 1, 11, 0, 1647907933365);
insert into fbg_value (id, physical_value_info_id, value, tag, create_time) values (7, 2, 22, 0, 1647907933365);
insert into fbg_value (id, physical_value_info_id, value, tag, create_time) values (8, 2, 22, 0, 1647907933365);



drop table if exists `fbg_value_info`;
create table `fbg_value_info`(
    `id` bigint not null comment 'id',
    `property_name` varchar(20) not null comment '传感器名',
    `min` decimal(10,4) not null comment '最小值',
    `max` decimal(10,4) not null comment '最小值',
    `distance` bigint not null comment '与光纤光栅解调仪的距离',
    `create_time` bigint(13) not null comment '创建时间'
)engine=innodb default charset = utf8mb4 comment '光纤光栅传感阵列物理属性表';
alter table fbg_value_info add category int(4);
alter table fbg_value_info add unique key(id);
alter table fbg_value_info drop primary key;

drop table if exists `user`;
create table `user`(
                       `id` bigint not null comment 'ID',
                       `login_name` varchar(50) not null comment '登陆名',
                       `password` char(32) not null comment '密码',
                       primary key (`id`),
                       unique key `login_name_unique` (`login_name`)
)engine = innodb default charset = utf8mb4 comment = '用户';

insert into `user` (id, `login_name`, `password`) values (1, 'test','test');

drop table if exists `breakpoint_record`;
create table `breakpoint_record`(
    `id` bigint not null auto_increment comment 'ID',
    `tag` tinyint unique not null comment '断点位置（0表示没有，1表示第一个位置断了，以此类推）',
    `create_time` bigint(13) not null comment '创建时间-距离1970年1月1日的毫秒数',
    `state` tinyint default 0 comment  '是否维修完成（0表示没有，1表示已经完成维修）',
    `detail` varchar(40) default null comment '详情',
    primary key (`id`)
)engine = innodb default charset = utf8mb4 comment = '断点记录表（会不断生成同一个断点的位置记录）';
#查询最新时间的记录：计算传感器在线率
select tag from breakpoint_record order by create_time desc limit 1;

show variables like '%event_scheduler%';
#获取前一小时的时间
select date_sub(now(), interval 1 hour);

select min(create_time) create_time, tag from fbg_value where (create_time > 3600 and tag != 0) group by tag;
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



select * from fbg_value a left join fbg_value_info b
    on a.physical_value_info_id = b.id
where a.value not between b.min
    and a.create_time between #{startTime} and #{endTime}
    and b.max order by a.create_time;

#  联合fbg_value和fbg_value_info两个表来进行查询：
#  条件1：fbg_value_info的category为2
#  条件2：fbg_value的create_time最大
select value from fbg_value a left join fbg_value_info b
    on a.physical_value_info_id = b.id
where b.category = 2 order by a.create_time desc limit 1;

/**
 * 查询过去24小时的温度最大最小值
 * 联合fbg_value和fbg_value_info两个表来进行查询：
 * 条件1：fbg_value_info的category为2
 * 条件2：fbg_value 为最小 或者 最大
 * 条件3：按照时间范围查询
 * 传入时间参数
 * 要得到每个小时的最小值以及最大值
 * @return
 */
select min(value), max(value) from fbg_value a left join fbg_value_info b
on a.physical_value_info_id = b.id
where b.category = 2
and a.create_time between #{lastNHoursStart} and #{lastNHoursEnd}

drop table if exists `arc`;
create table `arc`(
       `id` bigint not null comment 'ID',
       `length` decimal(10,4) not null comment '弧长长度',
        primary key (`id`)
)engine = innodb default charset = utf8mb4 comment = '弧长参数设置';
insert into arc (`id`, `length`) values (1, 0.3)

select length from arc limit 1
