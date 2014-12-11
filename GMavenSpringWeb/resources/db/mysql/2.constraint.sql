ALTER TABLE am_user ADD UNIQUE `user_cd_lang_index`(`user_cd`, `lang`);
ALTER TABLE am_user ADD UNIQUE `mobile_number_lang_index`(`mobile_number`, `lang`);
ALTER TABLE am_user ADD UNIQUE `email_lang_index`(`email`, `lang`);