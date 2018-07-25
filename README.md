Instructions to run:


SQL
================
drop table block;
drop table page;
drop table champion;

create table block (id integer not null, down integer, image varchar(255), lane varchar(255), name varchar(255), up integer, page_id integer not null, primary key (id));
create table champion (id integer not null, name varchar(255), primary key (id));
create table page (id integer not null, name varchar(255), relation varchar(255), primary key (id));

Eclipse
===============
1.  In src/main/java run com.lolpick.lolcounter.application.Application
2.  Terminate after zoe.  The program hangs.