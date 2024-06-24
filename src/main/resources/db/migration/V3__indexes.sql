create index idx_country on country (english_name);
create index idx_language_country on country_language (ID_COUNTRY, ENGLISH_NAME);