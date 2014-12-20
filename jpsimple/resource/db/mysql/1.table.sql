/*==============================================================*/
/* DROP ALL TABLE                                              */
/*==============================================================*/
drop table if exists am_user_role;
drop table if exists am_user_detail;
drop table if exists am_role;
drop table if exists am_user;
drop table if exists bm_code_name;
drop table if exists bm_config;
drop table if exists bm_record;
drop table if exists bm_menu;
drop table if exists cm_article_comment;
drop table if exists cm_article;
drop table if exists cm_forum_reply;
drop table if exists cm_forum_question;
drop table if exists cm_forum_category;
drop table if exists cm_link;
drop table if exists cm_vocabulary;
drop table if exists em_product;

/*==============================================================*/
/* Table: am_role                                               */
/*==============================================================*/
create table am_role
(
   role_id              char(32) not null,
   role_cd              varchar(32),
   role_name            varchar(64),
   description          varchar(1024),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: am_user                                               */
/*==============================================================*/
create table am_user
(
   user_id              char(32) not null,
   user_cd              varchar(32),
   user_name            varchar(30),
   user_alias           varchar(30),
   user_level           numeric(9,0) default 0,
   user_score           numeric(9,0) default 0,
   login_times          numeric(9,0) default 0,
   password             varchar(128),
   gender               char(1),
   qq                   varchar(15),
   email                varchar(64),
   birth_year           char(4),
   birth_month          char(2),
   birth_day            char(2),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: am_user_detail                                        */
/*==============================================================*/
create table am_user_detail
(
   user_detail_id       char(32) not null,
   user_id              char(32),
   mobile_phone         varchar(20),
   home_phone           varchar(20),
   country_id           char(32),
   province_id          char(32),
   city_id              char(32),
   birth_place          varchar(255),
   live_place           varchar(255),
   annual_income        decimal(24,8) default 0,
   job_category_pid     char(32),
   job_category_id      char(32),
   job_position_id      char(32),
   company_name         varchar(64),
   shool_name           varchar(64),
   education_id         char(32),
   interest_ids         varchar(255),
   password_tip_custom  varchar(50),
   password_tip_id      char(32),
   password_tip_answer  varchar(50),
   signature            varchar(255),
   attach               blob,
   attach_name          varchar(125),
   remark               varchar(255),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (user_detail_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: am_user_role                                          */
/*==============================================================*/
create table am_user_role
(
   user_role_id         char(32) not null,
   role_id              char(32),
   user_id              char(32),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (user_role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: bm_code_name                                          */
/*==============================================================*/
create table bm_code_name
(
   code_name_id         char(32) not null,
   code_name_pid        char(32),
   code                 varchar(32),
   name                 varchar(64),
   display_no           numeric(9,0) default 0,
   display_level        numeric(9,0) default 0,
   end_flag             char(1) comment '0:no, 1:yes',
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (code_name_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: bm_config                                             */
/*==============================================================*/
create table bm_config
(
   config_id            char(32) not null,
   config_key           varchar(32),
   config_key_display_name varchar(64),
   config_value         varchar(32),
   description          varchar(1024),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (config_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: bm_record                                             */
/*==============================================================*/
create table bm_record
(
   record_id            char(32) not null,
   table_name           varchar(64),
   operate_type_id      char(32),
   operator_id          char(32),
   operator_name        varchar(30),
   remark               varchar(255),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (record_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: bm_menu                                               */
/*==============================================================*/
create table bm_menu
(
   menu_id              char(32),
   menu_pid             char(32),
   menu_cd              varchar(32),
   menu_name            varchar(64),
   menu_type            char(4),
   url                  varchar(256),
   belong_user_id       char(32),
   display_level        numeric(9,0) default 0,
   display_no           numeric(9,0) default 0,
   end_flag             char(1) comment '0:no, 1:yes',
   modify_timestamp     timestamp,
   create_date_time     datetime,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (menu_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: cm_article                                            */
/*==============================================================*/
create table cm_article
(
   article_id           char(32) not null,
   article_category_ids varchar(350),
   article_cd           varchar(32),
   article_title        varchar(64),
   article_content      text,
   comment_author_id    char(32),
   comment_author_name  varchar(30),
   article_recommend_level_id char(32),
   display_no           numeric(9,0) default 0,
   remark               varchar(255),
   audit_flag           char(1) comment '0：not audit, 1：submit, 2：pass, 3：reject, 4：modify',
   operator_id          char(32),
   operator_name        varchar(30),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (article_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: cm_article_comment                                    */
/*==============================================================*/
create table cm_article_comment
(
   article_comment_id   char(32) not null,
   article_id           char(32),
   comment_content      text,
   comment_author_id    char(32),
   comment_author_name  varchar(30),
   display_no           numeric(9,0) default 0,
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (article_comment_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: cm_forum_category                                     */
/*==============================================================*/
create table cm_forum_category
(
   forum_category_id    char(32) not null,
   forum_category_pid   char(32),
   forum_category_cd    varchar(32),
   forum_category_name  varchar(64),
   forum_moderator_id   char(32),
   forum_moderator_name varchar(30),
   display_level        numeric(9,0) default 0,
   display_no           numeric(9,0) default 0,
   enable_flag          char(1) comment '0：don''''t enable, 1：has enable',
   end_flag             char(1) comment '0:no, 1:yes',
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (forum_category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: cm_forum_question                                     */
/*==============================================================*/
create table cm_forum_question
(
   forum_question_id    char(32) not null,
   forum_category_id    char(32),
   question_title       varchar(255),
   question_comment     text,
   question_author_id   char(32),
   question_author_name varchar(30),
   reply_number         numeric(9,0) default 0,
   question_recommend_level_id char(32),
   audit_flag           char(1) comment '0：not audit, 1：submit, 2：pass, 3：reject, 4：modify',
   display_no           numeric(9,0) default 0,
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (forum_question_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: cm_forum_reply                                        */
/*==============================================================*/
create table cm_forum_reply
(
   forum_reply_id       char(32) not null,
   forum_question_id    char(32),
   reply_coment         text,
   reply_author_id      char(32),
   reply_author_name    varchar(30),
   best_flag            char(1),
   use_score            numeric(9,0) default 0,
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (forum_reply_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: cm_link                                               */
/*==============================================================*/
create table cm_link
(
   link_id              char(32) not null,
   link_name            varchar(64),
   link_url             varchar(255),
   link_catagory_id     varchar(32),
   link_catagory_name   varchar(64),
   display_no           numeric(9,0) default 0,
   operator_id          char(32),
   operator_name        varchar(30),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (link_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: cm_vocabulary                                         */
/*==============================================================*/
create table cm_vocabulary
(
   vocabulary_id        char(32) not null,
   foreign_language     varchar(255),
   phonogram            varchar(64),
   chinese              varchar(255),
   property_catagory_ids varchar(255),
   vocabulary_catagory_ids varchar(255),
   level_id             char(32),
   recommend_state      char(1),
   display_no           numeric(9,0) default 0,
   description          varchar(1024),
   operator_id          char(32),
   operator_name        varchar(30),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (vocabulary_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: em_product                                            */
/*==============================================================*/
create table em_product
(
   product_id           char(32) not null,
   product_cd           varchar(32),
   product_name_cn      varchar(64),
   product_name_en      varchar(64),
   price                decimal(16,8) default 0,
   imagepath            varchar(100),
   description          text,
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;