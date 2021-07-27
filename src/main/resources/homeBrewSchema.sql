drop table if exists homebrew CASCADE; 
create table homebrew (id integer primary key AUTO_INCREMENT, 
type varchar(255), name varchar(255), percentage integer not null, brew_time integer not null);