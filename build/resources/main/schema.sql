create table if not exists user (
	id int primary key auto_increment,
	email varchar(255),
	pw varchar(255),
	created_at timestamp default current_timestamp,
	updated_at timestamp default current_timestamp
);

create table IF NOT EXISTS article (
 	id int primary key auto_increment,
	title varchar(255),
	content varchar(255),
	writer varchar(255),
	tags varchar(255),
	created_at timestamp default current_timestamp,
	updated_at timestamp default current_timestamp
);

create table if not exists tag (
	id int primary key auto_increment,
	user_id int,
	article_id int,
	root_tag_id int,
	created_at timestamp default current_timestamp,
	updated_at timestamp default current_timestamp
);