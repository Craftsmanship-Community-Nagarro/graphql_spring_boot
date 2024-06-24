--Insert some sample data
insert into country (ENGLISH_NAME, VERSION, CREATED_BY, CREATED_DATE)
values ('United Kingdom', 0, 'script', CURRENT_TIMESTAMP());
insert into country (ENGLISH_NAME, VERSION, CREATED_BY, CREATED_DATE)
values ('Spain', 0, 'script', CURRENT_TIMESTAMP());
insert into country (ENGLISH_NAME, VERSION, CREATED_BY, CREATED_DATE)
values ('France', 0, 'script', CURRENT_TIMESTAMP());
insert into country (ENGLISH_NAME, VERSION, CREATED_BY, CREATED_DATE)
values ('Mexico', 0, 'script', CURRENT_TIMESTAMP());
insert into country (ENGLISH_NAME, VERSION, CREATED_BY, CREATED_DATE)
values ('Austria', 0, 'script', CURRENT_TIMESTAMP());
insert into country (ENGLISH_NAME, VERSION, CREATED_BY, CREATED_DATE)
values ('Philippines', 0, 'script', CURRENT_TIMESTAMP());


insert into country_language (ID_COUNTRY, ENGLISH_NAME, VERSION, CREATED_BY, CREATED_DATE)
values ((select id from country where english_name = 'United Kingdom'), 'English', 0, 'script', CURRENT_TIMESTAMP());
insert into country_language (ID_COUNTRY, ENGLISH_NAME, VERSION, CREATED_BY, CREATED_DATE)
values ((select id from country where english_name = 'Spain'), 'Spanish', 0, 'script', CURRENT_TIMESTAMP());
insert into country_language (ID_COUNTRY, ENGLISH_NAME, VERSION, CREATED_BY, CREATED_DATE)
values ((select id from country where english_name = 'France'), 'French', 0, 'script', CURRENT_TIMESTAMP());
insert into country_language (ID_COUNTRY, ENGLISH_NAME, VERSION, CREATED_BY, CREATED_DATE)
values ((select id from country where english_name = 'Mexico'), 'Spanish', 0, 'script', CURRENT_TIMESTAMP());
insert into country_language (ID_COUNTRY, ENGLISH_NAME, VERSION, CREATED_BY, CREATED_DATE)
values ((select id from country where english_name = 'Austria'), 'German', 0, 'script', CURRENT_TIMESTAMP());
insert into country_language (ID_COUNTRY, ENGLISH_NAME, VERSION, CREATED_BY, CREATED_DATE)
values ((select id from country where english_name = 'Philippines'), 'English', 0, 'script', CURRENT_TIMESTAMP());
insert into country_language (ID_COUNTRY, ENGLISH_NAME, VERSION, CREATED_BY, CREATED_DATE)
values ((select id from country where english_name = 'Philippines'), 'Filipino', 0, 'script', CURRENT_TIMESTAMP());