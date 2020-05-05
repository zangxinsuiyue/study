create table Comment
(
    id bigint auto_increment comment '评论的自己的id',
    parent_id bigint not null comment '被回复评论的id',
    type int not null comment '判断自身是几级评论',
    commentator int not null comment '评论人的id',
    gmt_create bigint not null comment '评论时间',
    gmt_modified bigint not null comment '修改时间',
    like_count bigint default 0 null comment '点赞数',
    constraint Comment_pk
        primary key (id)
);

