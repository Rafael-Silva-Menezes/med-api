CREATE TABLE doctors
(
    id             bigint       NOT NULL auto_increment,
    name           VARCHAR(100) NOT NULL,
    email          VARCHAR(100) NOT NULL UNIQUE,
    crm            VARCHAR(6)   NOT NULL UNIQUE,
    specialization VARCHAR(100) NOT NULL,
    street         VARCHAR(100) NOT NULL,
    neighborhood   VARCHAR(100) NOT NULL,
    zip_code       VARCHAR(9)   NOT NULL,
    complement     VARCHAR(100),
    house_number   VARCHAR(20),
    state          CHAR(2)      NOT NULL,
    city           VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)

);

