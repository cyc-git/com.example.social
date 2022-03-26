create table user
(
    id         bigint auto_increment
        primary key,
    name       varchar(100) not null,
    account    varchar(255) not null,
    created_at bigint       not null,
    updated_at bigint       not null,
    deleted_at bigint       null,
    constraint user_un
        unique (account)
);

create table user_password
(
    user_id  bigint       not null
        primary key,
    password varchar(100) not null,
    constraint user_password_FK
        foreign key (user_id) references user (id)
            on update cascade on delete cascade
);

create table poster
(
    id         bigint       not null comment 'id'
        primary key,
    name       varchar(100) not null comment 'poster name',
    account    varchar(255) not null comment 'poster account',
    deleted_at bigint       null comment 'the time when the poster was deleted, may be null, means the poster not been deleted yet.',
    constraint poster_un
        unique (account)
);

create table article
(
    id                 bigint auto_increment comment 'id'
        primary key,
    poster_id          bigint not null comment 'the poster id who posted this article',
    content            text   not null comment 'the posted content',
    posted_at          bigint not null comment 'the posted time',
    updated_at         bigint not null comment 'the last update time',
    sharing_article_id bigint null comment 'the article id shared by this article, may be null, means this article did not share any article',
    constraint article_FK
        foreign key (poster_id) references poster (id),
    constraint article_FK_1
        foreign key (sharing_article_id) references article (id)
);

create table article_favorite
(
    article_id   bigint not null comment 'the article id',
    favorited_by bigint not null comment 'the poster id who favorited the article',
    favorited_at bigint not null comment 'the favorited time',
    primary key (article_id, favorited_by),
    constraint article_favorite_FK
        foreign key (article_id) references article (id),
    constraint article_favorite_FK_1
        foreign key (favorited_by) references poster (id)
);

create table article_reply
(
    id         bigint auto_increment comment 'id'
        primary key,
    article_id bigint not null comment 'the article id which this reply replied to',
    content    text   not null comment 'the replied content',
    replied_by bigint not null comment 'the poster id who replied the article',
    replied_at bigint not null comment 'the replied time',
    constraint article_reply_FK
        foreign key (article_id) references article (id)
            on update cascade on delete cascade,
    constraint article_reply_FK_1
        foreign key (replied_by) references poster (id)
);

create table article_star
(
    article_id bigint not null comment 'the article id',
    stared_by  bigint not null comment 'the poster id who stared the article',
    stared_at  bigint not null comment 'the stared time',
    primary key (article_id, stared_by),
    constraint article_star_FK
        foreign key (article_id) references article (id),
    constraint article_star_FK_1
        foreign key (stared_by) references poster (id)
);

create table poster_follow
(
    poster_id   bigint not null comment 'the poster id who was been followed',
    followed_by bigint not null comment 'the poster id of the follower',
    followed_at bigint not null comment 'the followed time',
    primary key (poster_id, followed_by),
    constraint poster_follower_FK
        foreign key (poster_id) references poster (id),
    constraint poster_follower_FK_1
        foreign key (followed_by) references poster (id)
);
