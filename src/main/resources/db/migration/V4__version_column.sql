alter table country
    add column version BIGINT default 0 not null;
alter table country_language
    add column version BIGINT default 0 not null;