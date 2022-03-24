create table user
(
    id         bigint auto_increment
        primary key,
    name       varchar(100) not null,
    account    varchar(100) not null,
    created_at bigint       not null,
    updated_at bigint       not null,
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

