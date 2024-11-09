CREATE TABLE "user"
(
    id         VARCHAR(255) NOT NULL,
    first_name VARCHAR(30)  NOT NULL,
    last_name  VARCHAR(30)  NOT NULL,
    email      VARCHAR(40),
    phone      VARCHAR(25),
    CONSTRAINT pk_user PRIMARY KEY (id)
);
