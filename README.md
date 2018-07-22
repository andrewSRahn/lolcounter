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
In src/test/java
1.  run com.lolpick.lolcounter.ChampionScrapeTest

In src/main/java
2.  run com.lolpick.lolcounter.Application

In src/main/resources
3.  fails.txt contains failed http requests.  run those manually.
^lol 