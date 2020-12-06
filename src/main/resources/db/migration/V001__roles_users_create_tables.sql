create table if not exists roles
(
    name       varchar(255) not null
        constraint roles_pkey
            primary key,
    privileged boolean      not null
);

create table if not exists users
(
    email      varchar(319) not null
        constraint users_pkey
            primary key,
    hash       varchar(60),
    role_name  varchar(255)
        constraint fk6e7f1kfvvn2k48olww485qvo3
            references roles
);