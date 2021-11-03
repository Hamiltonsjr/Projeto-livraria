create table profile (
    id int AUTO_INCREMENT not null,
    profile varchar(255) not null,
    primary key (id));
    commit;
    insert into profile (profile) values ("Seller"), ("Administrator");