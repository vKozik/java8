create table Food
(
   ID integer not null,
   NAME varchar2(255) not null,
   CALORIES NUMBER,
   DATE_OF_EATING TIMESTAMP,
   primary key(id)
);

create sequence Food_seq START WITH 10100;
