create sequence hibernate_sequence start with 1 increment by 1;
create table PLAYER (ID integer not null, FIRSTNAME varchar(255), POSITION varchar(255), RATING integer, SURNAME varchar(255), TEAM_ID integer, primary key (ID));
create table SALARY (ID integer not null, CHARITABLE_CONTRIBUTION integer, GROSS integer, MEDICAL_CONTRIBUTION integer, NET integer, PLAYER_ID integer, primary key (ID));
create table TEAM (ID integer not null, LOCATION varchar(255), NAME varchar(255), primary key (ID));
create table TEAM_COLOUR (ID integer not null, HTMLCODE varchar(255), NAME varchar(255), TEAM_ID integer, primary key (ID));
alter table PLAYER add constraint FK_TEAM_ID foreign key (TEAM_ID) references TEAM;
alter table SALARY add constraint FK_PLAYER_ID foreign key (PLAYER_ID) references PLAYER;
alter table TEAM_COLOUR add constraint FK_TEAM_ID foreign key (TEAM_ID) references TEAM;