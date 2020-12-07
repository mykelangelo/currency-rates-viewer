CREATE TABLE IF NOT EXISTS "roles"
(
    "name"       VARCHAR NOT NULL PRIMARY KEY,
    "privileged" BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS "users"
(
    "email"     VARCHAR NOT NULL PRIMARY KEY,
    "hash"      VARCHAR,
    "role_name" VARCHAR REFERENCES "roles"
);
