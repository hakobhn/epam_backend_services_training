-- Init database for postgres

CREATE TABLE IF NOT EXISTS states
(
    id serial not null primary key,
    content character varying(255) NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone
);

CREATE TABLE IF NOT EXISTS users
(
    id serial not null primary key,
    f_name character varying(255) NOT NULL,
    l_name character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    roles character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone
);

CREATE INDEX idx_users_email
ON users(email);