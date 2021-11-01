CREATE TABLE products (id serial, title varchar(100), price numeric(6, 2), views int DEFAULT (0));

 INSERT INTO products (title, price)
 VALUES
 ('Product #1', 10),
 ('Product #2', 20),
 ('Product #3', 30),
 ('Product #4', 40),
 ('Product #5', 50),
 ('Product #6', 60),
 ('Product #7', 70),
 ('Product #8', 80),
 ('Product #9', 90),
 ('Product #10', 40),
 ('Product #11', 60),
 ('Product #12', 50),
 ('Milk #2', 80),
 ('Milk', 70);


CREATE TABLE users (
    id serial,
    username varchar(50) NOT NULL,
    password varchar(80) NOT NULL,
    name varchar(100) NOT NULL,
    email varchar(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE roles (
    id serial,
    name varchar(50) DEFAULT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE users_roles (
    user_id int NOT NULL,
    role_id int NOT NULL,
    PRIMARY KEY(user_id, role_id),

    CONSTRAINT FK_USER_ID_01 FOREIGN KEY (user_id)
    REFERENCES users (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION,

    CONSTRAINT FK_ROLE_ID FOREIGN KEY (role_id)
    REFERENCES roles (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO roles (name)
VALUES
('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO users (username, password, name, email)
VALUES
('admin',
'$2a$12$.MFh95xyO6sHS8QX0rSH4em8W6Ooser3nDoYEqkSitZuVsSVwoyuS',
'Bob Johnson', 'bob@gmail.com');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1), (1, 2);

