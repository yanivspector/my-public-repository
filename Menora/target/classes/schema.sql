

DROP TABLE product_table;
DROP TABLE event_table;

-- create table company_table
-- (
--     id      INT NOT NULL,
--     name    varchar(255),
--     EVENT_ID varchar(255),
--     primary key (id)
-- );


create table event_table
(
    id        INT NOT NULL,
    companyName varchar(255),
    type      varchar(255),
    INSUREDID varchar(255),
    primary key (id)
);

create table product_table
(
    id        INT NOT NULL,
    type      varchar(255),
    price     varchar(255),
    startDate varchar(255),
    endDate   varchar(255),
    EVENT_ID varchar(255),
    primary key (id)
);
