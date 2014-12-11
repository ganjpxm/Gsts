/*==============================================================*/
/* Table: am_user                                               */
/*==============================================================*/
create table am_user
(
   user_id              char(32) not null,
   user_cd              varchar(32),
   first_name           varchar(64),
   last_name            varchar(64),
   gender               varchar(6),
   birthday             date,
   mobile_number        varchar(20),
   email                varchar(64),
   password             varchar(20),
   photo_url            varchar(256),
   login_count          numeric(9,0),
   description          text,
   lang                 varchar(10),
   operator_id          char(32),
   operator_name        varchar(128),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_status          char(1),
   primary key (user_id)
);

