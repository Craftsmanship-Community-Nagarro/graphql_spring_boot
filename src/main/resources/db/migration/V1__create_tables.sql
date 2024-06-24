create table country
(
    ID                 integer auto_increment,
    ENGLISH_NAME       varchar(100) not null,
    CREATED_BY         varchar(100) null,
    CREATED_DATE       timestamp    null,
    LAST_MODIFIED_BY   varchar(100) null,
    LAST_MODIFIED_DATE timestamp    null,
    PRIMARY KEY (ID)
);

create table IF NOT EXISTS country_language
(
    ID                 integer auto_increment,
    ID_COUNTRY         int          not null,
    ENGLISH_NAME       varchar(100) not null,
    CREATED_BY         varchar(100) null,
    CREATED_DATE       timestamp    null,
    LAST_MODIFIED_BY   varchar(100) null,
    LAST_MODIFIED_DATE timestamp    null,
    PRIMARY KEY (ID),
    FOREIGN KEY (ID_COUNTRY) REFERENCES country (ID)
);