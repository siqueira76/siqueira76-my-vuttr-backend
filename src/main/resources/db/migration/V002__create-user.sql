create table w_user (
       id integer generated by default as identity,
       email varchar(255),
       senha varchar(255),
       primary key (id)
);

create table perfis (
       user_id integer not null,
       perfis integer
);

alter table w_user 
    drop constraint if exists UK_6m79d35adq2tik2kvnji530px;
    
alter table w_user 
    add constraint UK_6m79d35adq2tik2kvnji530px unique (email);
    
alter table perfis 
    add constraint FK5dto3tn6cxdc9unpj5wpvlxmx 
    foreign key (user_id) 
    references w_user;