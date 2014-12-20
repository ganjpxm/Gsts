CREATE UNIQUE INDEX USER_CD_INDEX ON AM_USER (USER_CD);

alter table am_user_role add constraint FK_ro_to_us_ro foreign key (role_id)
      references am_role (role_id) on delete restrict on update restrict;

alter table am_user_role add constraint FK_us_to_us_ro foreign key (user_id)
      references am_user (user_id) on delete restrict on update restrict;

alter table cm_article_comment add constraint FK_ar_to_ar_co foreign key (article_id)
      references cm_article (article_id) on delete restrict on update restrict;

alter table cm_forum_question add constraint FK_fo_ca_to_fo_qu foreign key (forum_category_id)
      references cm_forum_category (forum_category_id) on delete restrict on update restrict;

alter table cm_forum_reply add constraint FK_fo_qu_to_fo_re foreign key (forum_question_id)
      references cm_forum_question (forum_question_id) on delete restrict on update restrict;      