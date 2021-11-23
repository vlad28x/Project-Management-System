--liquibase formatted sql

--changeset Vladislav Skibin:000000-create-table-users
create table users
(
    id          int8 generated by default as identity primary key,
    username    varchar(255) not null,
    password    varchar(255) not null,
    first_name  varchar(64)  not null,
    second_name varchar(64)  not null,
    email       varchar(255) not null,
    user_role   varchar(255) not null,
    project_id  int8
);

--changeset Vladislav Skibin:000001-create-table-project
create table project
(
    id                  int8 generated by default as identity primary key,
    project_name        varchar(255) not null,
    project_description varchar(255),
    project_start_date  date,
    project_end_date    date,
    project_status      varchar(255) not null,
    owner_id            int8         not null
);

--changeset Vladislav Skibin:000002-create-table-task

create table task
(
    id               int8 generated by default as identity primary key,
    task_name        varchar(255) not null,
    task_description varchar(255),
    task_created_at  timestamp    not null,
    task_updated_at  timestamp    not null,
    task_status      varchar(255) not null,
    task_type        varchar(255) not null,
    task_start_date  date,
    task_end_date    date,
    assigner_id      int8,
    owner_id         int8         not null,
    project_id       int8         not null,
    release_id       int8
);

--changeset Vladislav Skibin:000003-create-table-release

create table release
(
    id                  int8 generated by default as identity primary key,
    release_version     varchar(128) not null,
    release_description varchar(255),
    release_end_date    date,
    release_start_date  date
);

--changeset Vladislav Skibin:000004-add-foreign-key-constraints

alter table project
    add constraint FK_owner
        foreign key (owner_id)
            references users (id)
            on delete restrict
            on update cascade;
alter table task
    add constraint FK_assigner
        foreign key (assigner_id)
            references users (id)
            on delete set null
            on update cascade;
alter table task
    add constraint FK_owner
        foreign key (owner_id)
            references users (id)
            on delete cascade
            on update cascade;
alter table task
    add constraint FK_project
        foreign key (project_id)
            references project (id)
            on delete restrict
            on update cascade;
alter table task
    add constraint FK_release
        foreign key (release_id)
            references release (id)
            on delete restrict
            on update cascade;
alter table users
    add constraint FK_project
        foreign key (project_id)
            references project (id)
            on delete set null
            on update cascade;

--changeset Vladislav Skibin:000005-insert-data
insert into users(username, password, first_name, second_name, email, user_role)
values ('admin', '$2a$12$sdR.VIjcceKtIpgt3t/Xa.oWbPc0.qYzzV/vn9.5rCEqgM4q7qgmq', 'Владислав', 'Скибин',
        'vlad.skibin@mail.ru', 'ADMIN'),
       ('manager1', '$2a$12$wVWUhQwuAdYwoFqxg2nQCeah358v7HMjfuj5uM8tpkouOakNEpTiS', 'Виталий', 'Кудрявцев',
        'vital45@gmail.com', 'MANAGER'),
       ('manager2', '$2a$12$fOi7.NI/EZTvlImfI9Z8aulSx9CyLlWyxx4hEl8dNdGRbZhFmg.9K', 'Александра', 'Ястребова',
        'alexa@gmail.com', 'MANAGER'),
       ('programmer1', '$2a$12$9Y3MQ1dxCEZN1jqAp.RMaezS2UbndOULmqjXurPBP/IoO55JnE1dG', 'Степан', 'Ангельский',
        'stepa@gmail.com', 'PROGRAMMER'),
       ('programmer2', '$2a$12$j9x0hhy9I.tbZvWr4iG0FuOxyHmSnRM.5QY8n3emuXpv4wcLnM8Ly', 'Игорь', 'Ядронский',
        'igor@gmail.com', 'PROGRAMMER'),
       ('programmer3', '$2a$12$HseY7xj9T/GDXRDw2VN3S.XO8TL1PfeAS12gXnSI.8Xi3fZ954EQe', 'Николай', 'Николаев',
        'kolya@gmail.com', 'PROGRAMMER'),
       ('programmer4', '$2a$12$K1Ib9msLnSOselQfgbO83.qCw/ut1cUhOhZog3Av0KwkXJ.zA6KQe', 'Ярослав', 'Абровский',
        'ydrovsky@gmail.com', 'PROGRAMMER');

insert into project(project_name, project_description, project_start_date, project_end_date, project_status, owner_id)
values ('Система управления проектами', 'Проект написан с использование Spring Boot', '2021-10-25', '2021-12-15',
        'IN_PROGRESS', 2),
       ('Система управления автомойкой', 'Проект написан с использование Django', '2021-09-25', '2022-01-15',
        'IN_PROGRESS', 3);

update users
set project_id=1
where id = 2;
update users
set project_id=1
where id = 3;
update users
set project_id=1
where id = 4;
update users
set project_id=2
where id = 5;
update users
set project_id=2
where id = 6;
update users
set project_id=1
where id = 7;

insert into release(release_version, release_description, release_end_date, release_start_date)
values ('1.0 Beta', 'Бета версия проекта', '2021-10-25', '2021-11-02'),
       ('1.0.2 Beta', 'Бета версия проекта', '2021-09-25', '2021-10-06');

insert into task(task_name, task_description, task_created_at, task_updated_at, task_status, task_type, task_start_date,
                 task_end_date, assigner_id, owner_id, project_id, release_id)
values ('TASK-1: Создать БД', 'Создать базу данных в СУБД PostgreSQL', (select current_timestamp),
        (select current_timestamp), 'BACKLOG', 'FEATURE', null, null, null, 2, 1, null),
       ('TASK-2: Архитектура приложения', 'Архитектура приложения', (select current_timestamp),
        (select current_timestamp), 'IN_PROGRESS', 'FEATURE', '2021-10-26', null, 4, 2, 1, 1),
       ('TASK-3: Сущности', 'Создать сущности', (select current_timestamp), (select current_timestamp), 'DONE',
        'FEATURE', '2021-10-27', '2021-10-30', 4, 2, 1, 1),
       ('TASK-4: Миграции', 'Добавить миграции', (select current_timestamp), (select current_timestamp), 'IN_PROGRESS',
        'FEATURE', '2021-10-26', null, 7, 2, 1, 1),
       ('TASK-5: Пофиксить баг', 'Пофиксить баг в контроллере', (select current_timestamp), (select current_timestamp),
        'IN_PROGRESS', 'BUG', '2021-10-26', null, 7, 2, 1, 1),
       ('TASK-1: Создать БД', 'Создать базу данных в СУБД PostgreSQL', (select current_timestamp),
        (select current_timestamp), 'BACKLOG', 'FEATURE', null, null, null, 3, 2, null),
       ('TASK-2: Архитектура приложения', 'Архитектура приложения', (select current_timestamp),
        (select current_timestamp), 'IN_PROGRESS', 'FEATURE', '2021-10-26', null, 6, 3, 2, 2),
       ('TASK-3: Сущности', 'Создать сущности', (select current_timestamp), (select current_timestamp), 'DONE',
        'FEATURE', '2021-10-27', '2021-10-30', 4, 3, 2, 2),
       ('TASK-4: Миграции', 'Добавить миграции', (select current_timestamp), (select current_timestamp), 'IN_PROGRESS',
        'FEATURE', '2021-10-26', null, 4, 3, 2, 2),
       ('TASK-5: Пофиксить баг', 'Пофиксить баг в контроллере', (select current_timestamp), (select current_timestamp),
        'IN_PROGRESS', 'BUG', '2021-10-26', null, 6, 3, 2, 2);