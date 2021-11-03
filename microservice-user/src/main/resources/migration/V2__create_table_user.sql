create table users (
    id int not null AUTO_INCREMENT,
    name varchar(255) not null,
    nickname varchar(255) not null,
    password varchar(255) not null,
    profile_id int,
    primary key (id),
    unique (nickname),
    foreign key (profile_id) references profile (id));
    commit;
