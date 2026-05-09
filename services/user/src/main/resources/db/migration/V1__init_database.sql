create table roles (
    id serial primary key,
    type varchar(50) not null unique
    );

create table addresses (
    id bigserial primary key,
    street varchar(200) not null,
    city varchar(200) not null,
    home varchar(50) not null,
    CONSTRAINT chk_address_street_not_blank CHECK (LENGTH(TRIM(street)) > 0),
    CONSTRAINT chk_address_city_not_blank   CHECK (LENGTH(TRIM(city))   > 0),
    CONSTRAINT chk_address_home_not_blank   CHECK (LENGTH(TRIM(home))   > 0)
);

CREATE TABLE users (
    id bigserial primary key,

    username varchar(50) not null unique,
    email varchar(255) not null unique,
    first_name varchar(100),
    last_name varchar(100),
    password varchar(255) not null,
    is_active boolean not null default true,
    birth_date date,

    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),

    role_id int not null,
    address_id bigint,
    constraint fk_user_role foreign key (role_id) references roles(id) on delete restrict,
    constraint fk_user_address foreign key (address_id) references addresses(id) on delete set null,

    CONSTRAINT chk_user_username_not_blank CHECK (LENGTH(TRIM(username)) >= 3),
    CONSTRAINT chk_user_email_not_blank    CHECK (LENGTH(TRIM(email))    > 0),
    CONSTRAINT chk_user_password_min_len   CHECK (LENGTH(password)       >= 60),
    CONSTRAINT chk_user_birth_date_past    CHECK (birth_date IS NULL OR birth_date < CURRENT_DATE)
);

CREATE INDEX idx_user_role_id    ON users(role_id);
CREATE INDEX idx_user_address_id ON users(address_id);
CREATE INDEX idx_user_is_active  ON users(is_active);

