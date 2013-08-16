/**
create table user{
   id int not null,
   username varchar(50),
   email varchar(50) not null unique,
   password varchar(50) not null,
   primary key (id)
};

CREATE TABLE IF NOT EXISTS `user`
  (  `id` int  NOT NULL AUTO_INCREMENT,  
 		`username` varchar(255) ,  
 		`email` varchar(255) NOT NULL unique,  
 		`password`varchar(255) NOT NULL,  
 		 PRIMARY KEY (`id`)
  ) ENGINE=MyISAM  DEFAULT CHARSET=utf8 ;
  
  **/