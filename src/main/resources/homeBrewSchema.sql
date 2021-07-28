drop table if exists brew CASCADE; 
create table brew (id integer primary key AUTO_INCREMENT, 
type varchar(255), name varchar(255), percentage integer not null, brew_time integer not null);