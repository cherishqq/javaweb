/**
**/

create table user{
   id int not null,
   username varchar(50),
   email varchar(50) not null unique,
   password varchar(50) not null,
   primary key (id)
  
};