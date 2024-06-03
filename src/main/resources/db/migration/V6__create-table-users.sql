CREATE TABLE users
(
    id       bigint       NOT NULL auto_increment,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255)  NOT NULL,
    PRIMARY KEY (id)

);