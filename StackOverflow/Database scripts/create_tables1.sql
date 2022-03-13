drop schema stack_overflow;
create schema stack_overflow;
use stack_overflow;

create table user_data(
user_id int not null auto_increment,
username varchar(25),
user_password varchar(25),
score int DEFAULT 0,
primary key(user_id)
);

create table tags(
tag_id int not null auto_increment,
tag_name varchar(25),
primary key(tag_id)
);

create table entry(
entry_id int not null auto_increment,
author_id int,
entry_text varchar(500),
creation_date DATETIME DEFAULT CURRENT_TIMESTAMP,
primary key(entry_id),
foreign key(author_id) references user_data(user_id)
);

create table question(
question_id int not null auto_increment,
entry_id int,
title varchar(25),
primary key(question_id),
foreign key(entry_id) references entry(entry_id)
);

create table answer(
answer_id int not null auto_increment,
entry_id int,
question_answered_id int,
primary key(answer_id),
foreign key(entry_id) references entry(entry_id),
foreign key(question_answered_id) references question(question_id)
);

create table question_tags(
id int not null auto_increment,
question_id int,
tag_id int,
primary key(id),
foreign key(question_id) references question(question_id),
foreign key(tag_id) references tags(tag_id)
);

create table votes(
vote_id int not null auto_increment,
user_id int,
vote_type int,
entry_id int,
primary key(vote_id),
foreign key(user_id) references user_data(user_id),
foreign key(entry_id) references entry(entry_id)
);