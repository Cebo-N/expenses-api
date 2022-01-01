drop database expensesdb;
drop user expenselogger;

create user expenselogger with password 'oniyance';
create database expensesdb with template=template0 owner=expenselogger;
\connect expensesdb;

alter default privileges grant all on tables to expenselogger;
alter default privileges grant all on sequences to expenselogger;

create table users(
    user_id integer primary key not null,
    first_name varchar(25) not null,
    last_name varchar(25) not null,
    email varchar(40) not null,
    password text not null,
    created_at DATE,
    last_login DATE
);

create table categories(
    category_id integer primary key not null,
    user_id integer not null,
    title varchar(25) not null,
    description varchar(100) not null
);

alter table categories add constraint cat_user_fk
foreign key (user_id) references users(user_id);

create table transactions(
    transaction_id integer primary key not null,
    category_id integer not null,
    user_id integer not null,
    amount numeric(10,2) not null,
    note varchar(50) not null,
    transaction_date bigint not null
);

alter table transactions add constraint trans_cat_fk
foreign key (category_id) references categories(category_id);

alter table transactions add constraint trans_user_cat_user_fk
foreign key (user_id) references users(user_id);

create sequence users_seq increment 1 start 1;
create sequence categories_seq increment 1 start 1;
create sequence transactions_seq increment 1 start 1000;






