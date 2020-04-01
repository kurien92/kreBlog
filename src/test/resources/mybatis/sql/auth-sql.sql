create table user (
    username varchar(20) not null primary key,
    password varchar(200) not null,
    enabled integer(1) not null
);

create table authorities (
    username varchar(20) not null,
    authority varchar(20) not null
);

create table group_members (
    group_id varchar(20) not null,
    username varchar(20) not null
);

create table groups (
    id varchar(20) not null,
    group_name varchar(20) not null
);

create table group_authorities (
    group_id varchar(20) not null,
    authority varchar(20) not null
);