--liquibase formatted sql

--changeset Vladislav Skibin:000000-create-table-account_balance
create table account
(
    id               int8 generated by default as identity primary key,
    account_username varchar(255) not null unique,
    account_balance  int8         not null
);

--changeset Vladislav Skibin:000001-create-table-history_balance
create table account_history
(
    id         int8 generated by default as identity primary key,
    account_id int8         not null,
    operation  varchar(255) not null,
    amount     int8         not null
);

--changeset Vladislav Skibin:000002-insert-data-payment
insert into account (account_username, account_balance)
values ('customer1', 250000),
       ('customer2', 100000);

insert into account_history(account_id, operation, amount)
values (1, 'TOP_UP', 100000),
       (1, 'TOP_UP', 150000),
       (2, 'TOP_UP', 50000),
       (2, 'TOP_UP', 50000);