alter table comment modify id bigint(30) auto_increment comment '评论的自己的id';
alter table comment modify parent_id bigint(30) not null comment '被回复评论的id';
alter table question modify id bigint(30) auto_increment;
alter table question modify creator bigint(30) null;
