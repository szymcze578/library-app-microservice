create table if not exists roles (
    id serial primary key,
    type varchar(50) unique
    );

create table if not exists addresses (
    id bigserial primary key,
    street varchar(255) not null,
    city varchar(255) not null,
    home varchar(255) not null
);

create table if not exists users (
    id bigserial primary key,
    username varchar(255) not null unique,
    email varchar(255) not null unique,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    password varchar(255) not null,
    is_active boolean default true,
    birth_date date,
    created_at timestamp default now(),
    updated_at timestamp default now(),
    role_id int not null,
    address_id bigint not null,
    constraint fk_user_role foreign key (role_id) references roles(id),
    constraint fk_user_address foreign key (address_id) references addresses(id)
);

