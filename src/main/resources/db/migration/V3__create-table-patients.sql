CREATE TABLE patients
(
    id           bigint       NOT NULL auto_increment,
    name         VARCHAR(100) NOT NULL,
    email        VARCHAR(100) NOT NULL UNIQUE,
    phone        VARCHAR(20)  NOT NULL UNIQUE,
    document     VARCHAR(14)  NOT NULL UNIQUE,
    street       VARCHAR(100) NOT NULL,
    neighborhood VARCHAR(100) NOT NULL,
    zip_code     VARCHAR(9)   NOT NULL,
    complement   VARCHAR(100),
    house_number VARCHAR(20),
    state        CHAR(2)      NOT NULL,
    city         VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)

);

