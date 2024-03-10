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

INSERT INTO states (content)
values ('MVC application'),
('Spring security'),
('OpenID connect'),
('Java Web Token'),
('User Authentication and Access Control'),
('OAuth and OpenID Connect'),
('REST application');

INSERT INTO users (f_name, l_name, email, roles, password)
values ('User', 'Test', 'user@gmail.com', 'USER', '$2a$10$opwjCa5GYHCvj8EL0IbO6uFBap8jbyPYvQSFx.Z/xPwcUNGIXYaE.'), -- test_pass
('Admin', 'Admin', 'admin@gmail.com', 'USER,ADMIN', '$2a$10$O.yvddIPqsTEtSyzMDej/exe4y.gUmWCq/cw/v97SmLgiyo1htJTa'), -- admin_pass
('Adam', 'Smith', 'adam@gmail.com', 'USER', '$2a$10$GeRHbFayD3vL89J0Nk6OkepZ0SG1nnZKXDkcMQSx59rj6uP.fa23a'), -- adam_pass
('John', 'Doe', 'John_Doe@gmail.com', 'USER', '$2a$10$NWhmtir4pnBTIuDiFvvUtut/V07EQXEeO/AluiCl/BDDKaSDX6p1O'), -- john_doe_pass
('Jane', 'Doe', 'Jane_Doe@gmail.com', 'USER', '$2a$10$Kxsr3kwhOOBNdmjOvs4DTeVcin/xOuJTNbf5t9p3v5qXnCILwrnLm'); -- jane_doe_pass

